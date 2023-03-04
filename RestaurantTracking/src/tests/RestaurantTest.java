package tests;

import app.Restaurant;
import factories.TableFactory;
import models.Table;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant createRestaurant(int tableCount) {
        Restaurant restaurant = new Restaurant(tableCount);
        for (int i = 0; i < tableCount; i++) {
            restaurant.addTable(TableFactory.createTable(i, (i + 1) * 2));
        }
        return restaurant;
    }

    @org.junit.jupiter.api.Test
    void createTable() {
        Restaurant restaurant = createRestaurant(18);
        assertEquals(restaurant.getTables().length, 18);
    }

    @org.junit.jupiter.api.Test
    void blockTable() {
        Restaurant restaurant = createRestaurant(18);
        restaurant.blockTable(0, 30);
        assertTrue(restaurant.getTables()[0].isBlocked(new java.util.Date()));
    }

    @org.junit.jupiter.api.Test
    void unblockTable() {
        Restaurant restaurant = createRestaurant(18);
        restaurant.blockTable(0, 30);
        restaurant.unblockTable(0);
        assertFalse(restaurant.getTables()[0].isBlocked(new java.util.Date()));
    }

    @org.junit.jupiter.api.Test
    void getTableBlockedUntil() {
        Restaurant restaurant = createRestaurant(18);
        restaurant.blockTable(0, 30);
        assertTrue(restaurant.getTableBlockedUntil(0).isPresent());
    }

    @org.junit.jupiter.api.Test
    void getAvailableTables() {
        Restaurant restaurant = createRestaurant(18);
        restaurant.blockTable(0, 30);
        assertEquals(restaurant.getAvailableTables(new java.util.Date()).size(), 17);
    }

    @org.junit.jupiter.api.Test
    void getAvailableTablesCapacity() {
        Restaurant restaurant = createRestaurant(18);
        assertEquals(restaurant.getAvailableTables(new java.util.Date(), 4).size(), 17);
        restaurant.blockTable(0, 30);
        assertEquals(restaurant.getAvailableTables(new java.util.Date(), 4).size(), 17);
        restaurant.blockTable(2, 30);
        assertEquals(restaurant.getAvailableTables(new java.util.Date(), 4).size(), 16);
    }

    private void checkTableOrdering(List<Table> tables) {
        tables.forEach(table -> {
            table.getBlockedUntil().ifPresent(date -> {
                assertTrue(date.after(new java.util.Date()));
            });
        });
    }

    @org.junit.jupiter.api.Test
    void getTablesByAvailability() {
        Restaurant restaurant = createRestaurant(18);
        restaurant.blockTable(0, 30);
        restaurant.blockTable(2, 25);
        restaurant.blockTable(4, 20);
        restaurant.blockTable(6, 15);
        restaurant.blockTable(8, 10);
        restaurant.blockTable(10, 5);
        List<Table> sortedTables = restaurant.getTablesByAvailability();
        assertEquals(sortedTables.get(sortedTables.size() - 1).getCapacity(), 2);
        assertEquals(sortedTables.get(0).getCapacity(), 4);
        checkTableOrdering(sortedTables);
    }

    @org.junit.jupiter.api.Test
    void fullTest() {
        Restaurant restaurant = createRestaurant(18);
        restaurant.blockTable(0, 30);
        restaurant.blockTable(2, 25);
        restaurant.blockTable(4, 20);
        restaurant.blockTable(6, 15);
        restaurant.blockTable(8, 10);
        restaurant.blockTable(10, 5);
        checkTableOrdering(restaurant.getTablesByAvailability());
        //re block the same table for extra time
        restaurant.blockTable(10, 100);
        checkTableOrdering(restaurant.getTablesByAvailability());
        restaurant.getTableBlockedUntil(0).ifPresent(date -> {
            assertTrue(date.after(new Date(System.currentTimeMillis() + (long) 20 * 60 * 1000)));
        });
    }
}
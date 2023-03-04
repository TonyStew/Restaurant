import app.Restaurant;
import factories.TableFactory;
import models.Table;

import java.util.List;

public class Example {
    public static void main(String[] args) {
        //Demonstrates an example usage for a restaurant with 18 tables
        int tableCount = 18;
        Restaurant restaurant = new Restaurant(tableCount);
        for (int i = 0; i < tableCount; i++) {
            restaurant.addTable(TableFactory.createTable(i, (i + 1) * 2));
        }

        restaurant.blockTable(0, 100);
        restaurant.blockTable(1, 45);
        restaurant.blockTable(2, 60);
        restaurant.blockTable(3, 75);
        restaurant.blockTable(4, 90);
        restaurant.blockTable(4, 2);

        List<Table> sortedTables = restaurant.getTablesByAvailability();
        System.out.println(sortedTables);
        //the restaurant host can use this to sort the tables
        //the available tables will be first followed by those that will become available soonest
        //they can then pick the table they want to assign to the customer and reserve it
        //like so:
        restaurant.blockTable(sortedTables.get(0).getTableId(), 60);
    }
}

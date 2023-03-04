package app;

import models.Table;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Restaurant.
 */
public class Restaurant {
    private final Table[] tables; //using an array because table quantity is given to be fixed

    /**
     * Instantiates a new Restaurant.
     *
     * @param tableCount the table count
     */
    public Restaurant(int tableCount) {
        this.tables = new Table[tableCount];
    }

    /**
     * Add table.
     *
     * @param table the table to add
     */
    public void addTable(Table table) {
        tables[table.getTableId()] = table;
    }

    /**
     * Get tables table [ ].
     *
     * @return the table [ ]
     */
    public Table[] getTables() {
        return tables;
    }

    /**
     * Block table.
     *
     * @param tableId the table id
     * @param minutes the minutes to block the table for
     */
    public void blockTable(int tableId, int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes must be positive");
        }
        validateTableId(tableId);
        tables[tableId].blockUntil(new Date(System.currentTimeMillis() + (long) minutes * 60 * 1000));
    }

    /**
     * Unblock table.
     *
     * @param tableId the table id
     */
    public void unblockTable(int tableId) {
        validateTableId(tableId);
        tables[tableId].unblock();
    }

    /**
     * Gets table blocked until.
     *
     * @param tableId the table id
     * @return the date until which the table is blocked
     */
    public Optional<Date> getTableBlockedUntil(int tableId) {
        validateTableId(tableId);
        return tables[tableId].getBlockedUntil();
    }

    /**
     * Gets available tables.
     *
     * @param date the date
     * @return the available tables for the given date
     */
    public List<Table> getAvailableTables(Date date) {
        return Arrays.stream(tables).filter(table -> !table.isBlocked(date)).toList();
    }

    /**
     * Gets available tables.
     *
     * @param date     the date
     * @param capacity the capacity
     * @return the available tables for the given date and capacity
     */
    public List<Table> getAvailableTables(Date date, int capacity) {
        return Arrays.stream(tables).filter(table -> !table.isBlocked(date) && table.getCapacity() >= capacity).toList();
    }

    /**
     * Gets tables by availability.
     *
     * @return the tables sorted by availability
     */
    public List<Table> getTablesByAvailability() {
        return Arrays.stream(tables).sorted().toList();
    }

    private void validateTableId(int tableId) {
        if (tableId < 0 || tableId >= tables.length) {
            throw new IllegalArgumentException("Requested table id is out of bounds");
        }
        if (tables[tableId] == null) {
            throw new IllegalArgumentException("Table " + tableId + " does not exist");
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(tables);
    }
}

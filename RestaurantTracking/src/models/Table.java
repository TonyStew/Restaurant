package models;

import java.util.Date;
import java.util.Optional;

/**
 * The type Table.
 */
public class Table implements Comparable<Table> {
    private final int capacity; //assumes table capacity is constant, easy to change if not
    private Date blockedUntil;
    private final int tableId;

    /**
     * Instantiates a new Table.
     *
     * @param capacity the capacity
     * @param tableId  the table id
     */
    public Table(int capacity, int tableId) {
        this.capacity = capacity;
        this.tableId = tableId;
    }

    /**
     * Gets table id.
     *
     * @return the table id
     */
    public int getTableId() {
    	return tableId;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Block until.
     *
     * @param date the date until which this table is to be blocked
     */
    public void blockUntil(Date date) {
        blockedUntil = date;
    }

    /**
     * Gets blocked until.
     *
     * @return the date until which this table is blocked
     */
    public Optional<Date> getBlockedUntil() {
        return Optional.ofNullable(blockedUntil);
    }

    /**
     * Is blocked boolean.
     *
     * @param date the date
     * @return if this table is blocked at the given date
     */
    public boolean isBlocked(Date date) {
        return blockedUntil != null && blockedUntil.after(date);
    }

    /**
     * Unblock.
     */
    public void unblock() {
        blockedUntil = null;
    }

    //compare by when tables will next be made available
    @Override
    public int compareTo(Table o) {
        if (blockedUntil == null && o.blockedUntil == null) {
            return 0;
        } else if (blockedUntil == null) {
            return -1;
        } else if (o.blockedUntil == null) {
            return 1;
        } else {
            return blockedUntil.compareTo(o.blockedUntil);
        }
    }

    @Override
    public String toString() {
        return "Table{" +
                "capacity=" + capacity +
                ", blockedUntil=" + blockedUntil +
                '}';
    }
}

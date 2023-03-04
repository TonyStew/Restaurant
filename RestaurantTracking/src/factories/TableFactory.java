package factories;

import models.Table;

public class TableFactory {
    //used to decouple the creation of tables from the restaurant
    public static Table createTable(int tableId, int capacity) {
        return new Table(capacity, tableId);
    }
}

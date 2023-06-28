package com.capstone.athenasinventorymanagementsystem;

import android.provider.BaseColumns;

public final class InventoryContract {
    private InventoryContract() {}

    public static class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "inventory";
        public static final String COLUMN_NAME_PRODUCT = "product";
        public static final String COLUMN_NAME_QUANTITY = "quantity";

        public static final String COLUMN_NAME_PRICE = "price";
    }
}

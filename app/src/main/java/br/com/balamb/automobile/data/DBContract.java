package br.com.balamb.automobile.data;

import android.provider.BaseColumns;

public final class DBContract {

    private DBContract() {}

    public static class AutomobileEntry implements BaseColumns {
        public static final String TABLE_NAME = "automobiles";
        public static final String COLUMN_MAKE = "make";
        public static final String COLUMN_MODEL = "model";
    }
}

package br.com.balamb.automobile.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "automobiles.bd";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DBContract.AutomobileEntry.TABLE_NAME + "(" +
                DBContract.AutomobileEntry._ID + " INTEGER PRIMARY KEY, " +
                DBContract.AutomobileEntry.COLUMN_MAKE + " TEXT, " +
                DBContract.AutomobileEntry.COLUMN_MODEL + " TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.AutomobileEntry.TABLE_NAME);
        onCreate(db);
    }
}
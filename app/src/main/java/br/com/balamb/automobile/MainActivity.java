package br.com.balamb.automobile;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.balamb.automobile.data.DBContract;
import br.com.balamb.automobile.data.Database;

public class MainActivity extends AppCompatActivity {

    final public static int FORM_REQUEST_CODE = 7;
    final public static String KEY_AUTOMOBILE = "key_automobile";

    private ArrayList<String> automobiles = new ArrayList<>();
    private AutomobileAdapter adapter;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        adapter = new AutomobileAdapter();
        listView.setAdapter(adapter);

        dbHelper = new Database(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DBContract.AutomobileEntry._ID,
                DBContract.AutomobileEntry.COLUMN_MAKE,
                DBContract.AutomobileEntry.COLUMN_MODEL
        };

        Cursor cursor = db.query(
                DBContract.AutomobileEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while(cursor.moveToNext()) {
                Automobile automobile = new Automobile(
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AutomobileEntry.COLUMN_MAKE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBContract.AutomobileEntry.COLUMN_MODEL))
                );
                addAutomobileToList(automobile, false);
            }
        }

        cursor.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("automobiles", automobiles);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == FORM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Automobile automobile = (Automobile) data.getSerializableExtra(KEY_AUTOMOBILE);
                addAutomobileToList(automobile, true);
            }
        }
    }

    private void addAutomobileToList(Automobile automobile, Boolean shouldSave) {
        adapter.addAutomobile(automobile);
        if (shouldSave) {
            dbHelper = new Database(MainActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DBContract.AutomobileEntry.COLUMN_MAKE, automobile.getMake());
            values.put(DBContract.AutomobileEntry.COLUMN_MODEL, automobile.getModel());

            db.insert(DBContract.AutomobileEntry.TABLE_NAME, null, values);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        startActivityForResult(intent, FORM_REQUEST_CODE);
    }
}

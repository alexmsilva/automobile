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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        adapter = new AutomobileAdapter();
        listView.setAdapter(adapter);

        if (savedInstanceState != null) {
            ArrayList<String> list = savedInstanceState.getStringArrayList("automobiles");
            if (list != null) {
                for (String s : list) {
                    String[] strings = s.split("-");
                    Automobile automobile = new Automobile(strings[0], strings[1]);
                    addAutomobileToList(automobile, false);
                }
            }
        }

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
        automobiles.add(String.format("%s-%s", automobile.getMake(), automobile.getModel()));
        adapter.addAutomobile(automobile);
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        startActivityForResult(intent, FORM_REQUEST_CODE);
    }
}

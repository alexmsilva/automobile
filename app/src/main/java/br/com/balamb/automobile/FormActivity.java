package br.com.balamb.automobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    EditText editMake;
    EditText editModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editMake = findViewById(R.id.edit_make);
        editModel = findViewById(R.id.edit_model);

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String make = editMake.getText().toString();
                String model = editModel.getText().toString();
                Automobile automobile = new Automobile(make, model);

                Intent intent = new Intent();
                intent.putExtra(MainActivity.KEY_AUTOMOBILE, automobile);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}

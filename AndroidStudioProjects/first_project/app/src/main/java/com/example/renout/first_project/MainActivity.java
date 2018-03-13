package com.example.renout.first_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Raphaël Renout");

        // Date d'anniversaire
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.DAY_OF_MONTH, 15);
        birthday.set(Calendar.MONTH, 1);
        birthday.set(Calendar.YEAR, 1995);

        // Aujourd'hui
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        TextView ageText = (TextView) findViewById(R.id.ageText);
        ageText.setText(getString(R.string.age, age));


        Toast.makeText(getBaseContext(),
                "Hello World",
                Toast.LENGTH_SHORT).show();

        Log.v("essai","Hello World");

    }
}

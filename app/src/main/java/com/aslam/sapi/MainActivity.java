package com.aslam.sapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCitySpinner();

        Button btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to the result activity
                Intent intent = new Intent(MainActivity.this, ShowResults.class);
                intent.putExtra("selectedCity", selectedCity);
                //finish();
                startActivity(intent);
            }
        });
    }

    private void setCitySpinner() {
        final Spinner spinnerCity = findViewById(R.id.spinnerCities);

        String[] cities = {"Colombo", "Kandy", "Galle", "Matale", "Matara"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = spinnerCity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}

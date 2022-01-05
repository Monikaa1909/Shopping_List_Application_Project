package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shoppinglistapplication.uiFormOfAccessibility.FormOfAccessibilityActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiUnitOfMeasurement.UnitsOfMeasurementActivity;

import java.util.Random;

public class SettingsActivity extends AppCompatActivity {

    private Button button_load_form_of_accessibility;
    private Button button_load_unit_of_measurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button_load_form_of_accessibility = findViewById(R.id.button_first_choice);
        button_load_unit_of_measurement = findViewById(R.id.button_second_choice);

        button_load_form_of_accessibility.setText(R.string.button_load_form_of_accessibility);
        button_load_unit_of_measurement.setText(R.string.button_load_list_of_measurment);

        button_load_unit_of_measurement.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, UnitsOfMeasurementActivity.class);
            startActivity(intent);
        });

        button_load_form_of_accessibility.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, FormOfAccessibilityActivity.class);
            startActivity(intent);
        });

    }
}
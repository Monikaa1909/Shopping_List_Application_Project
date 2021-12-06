package com.example.shoppinglistapplication.uiUnitOfMeasurement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.UnitOfMeasurementListAdapter;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;

public class UnitsOfMeasurementActivity extends AppCompatActivity {

    private static final int NEW_UNIT_ACTIVITY_REQUEST_CODE = 4;
    private UnitOfMeasurementViewModel unitOfMeasurementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UnitOfMeasurementListAdapter adapter = new UnitOfMeasurementListAdapter(new UnitOfMeasurementListAdapter.UnitOfMeasurementDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(this.getApplication());

        unitOfMeasurementViewModel.getAllUnits().observe(this, units -> {
            adapter.submitList(units);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(UnitsOfMeasurementActivity.this, NewUnitOfMeasurementActivity.class);
            startActivityForResult(intent, NEW_UNIT_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_UNIT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String unitName = data.getStringExtra(NewUnitOfMeasurementActivity.EXTRA_REPLY_UNIT);

            new Thread(() -> {
                unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(this.getApplication());

                if (!unitOfMeasurementViewModel.unitExists(unitName)) {
                    UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement(unitName);
                    unitOfMeasurementViewModel.insert(unitOfMeasurement, emptyFunction -> {});
                }
            }).start();

        } else {

        }
    }
}
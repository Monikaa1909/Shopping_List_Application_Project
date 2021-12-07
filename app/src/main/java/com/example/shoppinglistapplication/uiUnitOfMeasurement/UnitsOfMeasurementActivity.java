package com.example.shoppinglistapplication.uiUnitOfMeasurement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.UnitOfMeasurementListAdapter;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.uiCategories.NewCategoryActivity;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiFormOfAccessibility.FormOfAccessibilityActivity;
import com.example.shoppinglistapplication.uiFormOfAccessibility.FormOfAccessibilityToDeleteActivity;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class UnitsOfMeasurementActivity extends AppCompatActivity {

    public static final String KEY_UNIT_INFO = "unitInfo";
    private UnitOfMeasurementViewModel unitOfMeasurementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_add_delete_button);

        if (getIntent().getStringExtra(KEY_UNIT_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_UNIT_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.unit_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(UnitsOfMeasurementActivity.this, NewUnitOfMeasurementActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowej jednostki", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej jednostki",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

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
            startActivity(intent);
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(UnitsOfMeasurementActivity.this, UnitsOfMeasurementToDeleteActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
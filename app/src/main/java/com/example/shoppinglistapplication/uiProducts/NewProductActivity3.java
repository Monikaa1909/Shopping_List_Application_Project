package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.UnitOfMeasurementListAdapter2;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

public class NewProductActivity3 extends AppCompatActivity {

    private UnitOfMeasurementViewModel unitOfMeasurementViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz jednostkę miary nowego produktu:");

        String newProductName = getIntent().getStringExtra(NewProductActivity.KEY_NEW_PRODUCT_NAME);
        int newCategoryId = (int) getIntent().getSerializableExtra(NewProductActivity.KEY_CATEGORY_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UnitOfMeasurementListAdapter2 adapter = new UnitOfMeasurementListAdapter2(new UnitOfMeasurementListAdapter2.UnitOfMeasurementDiff(), 1, newProductName, newCategoryId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(this.getApplication());
        unitOfMeasurementViewModel.getAllUnits().observe(this, units -> {
            adapter.submitList(units);
        });
    }
}
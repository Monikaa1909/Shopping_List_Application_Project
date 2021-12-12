package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.UnitOfMeasurementListAdapter;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

public class NewProductInCategoryActivity2 extends AppCompatActivity {

    private UnitOfMeasurementViewModel unitOfMeasurementViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_without_button);
        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz jednostkę nowego produktu:");

//        String newProductName = getIntent().getStringExtra(ProductsByCategoryActivity.KEY_NEW_PRODUCT_NAME2);
//        int newCategoryId = (int) getIntent().getSerializableExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2);

        SimpleProductBuilder simpleProductBuilder = (SimpleProductBuilder) getIntent().getExtras().getSerializable("builder");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UnitOfMeasurementListAdapter adapter = new UnitOfMeasurementListAdapter(new UnitOfMeasurementListAdapter.UnitOfMeasurementDiff(), 2, simpleProductBuilder);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(this.getApplication());
        unitOfMeasurementViewModel.getAllUnits().observe(this, units -> {
            adapter.submitList(units);
        });
    }
}
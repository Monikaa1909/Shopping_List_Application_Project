package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter3;
import com.example.shoppinglistapplication.adapterholder.UnitOfMeasurementListAdapter;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.uiCategories.NewProductInCategoryActivity;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductUnitActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_edit_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Jednostka produktu:");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UnitOfMeasurementListAdapter adapter = new UnitOfMeasurementListAdapter(new UnitOfMeasurementListAdapter.UnitOfMeasurementDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int idProduct = (int) getIntent().getSerializableExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID);

        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());
            FloatingActionButton fab = findViewById(R.id.fab_edit);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(ProductUnitActivity.this, EditProductUnitActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                startActivity(intent);
                this.finish();
            });

            List<UnitOfMeasurement> unitsList = productViewModel.getProductUnit(idProduct);
            adapter.submitList(unitsList);
        }).start();

    }
}
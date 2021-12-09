package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.StartActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String KEY_PRODUCT_DETAIL_ID = "idProductDetail";
    private Button button_unit;
    private Button button_forms;
    private Button button_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        int idProduct = (int) getIntent().getSerializableExtra(KEY_PRODUCT_DETAIL_ID);

        button_category = findViewById(R.id.button_category);
        button_unit = findViewById(R.id.button_unit_of_measurement);
        button_forms = findViewById(R.id.button_form_of_accessibility);

        button_category.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailsActivity.this, ProductCategoryActivity.class);
            intent.putExtra(KEY_PRODUCT_DETAIL_ID, idProduct);
            startActivity(intent);
        });

        button_unit.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailsActivity.this, ProductUnitActivity.class);
            intent.putExtra(KEY_PRODUCT_DETAIL_ID, idProduct);
            startActivity(intent);
        });

        button_forms.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailsActivity.this, ProductFormsActivity.class);
            intent.putExtra(KEY_PRODUCT_DETAIL_ID, idProduct);
            startActivity(intent);
        });
    }
}
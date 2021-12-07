package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;

public class StartActivity extends AppCompatActivity {

//    private Button button_create_list_of_preferences;
    private Button button_load_list_of_preferences;
    private Button button_load_products;
    private Button button_load_categories;
    private Button button_load_dishes;
    private Button button_load_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        button_create_list_of_preferences = findViewById(R.id.button_create_new_list);
        button_load_list_of_preferences = findViewById(R.id.button_load_list);
        button_load_products = findViewById(R.id.button_load_products);
        button_load_categories = findViewById(R.id.button_load_categories);
        button_load_dishes = findViewById(R.id.button_load_dishes);
        button_load_settings = findViewById(R.id.button_load_settings);

//        button_create_list_of_preferences.setOnClickListener(view -> {
//            Intent intent = new Intent(StartActivity.this, NewListOfPreferencesActivity.class);
//            startActivity(intent);
//        });

        button_load_list_of_preferences.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, ListsOfPreferencesActivity.class);
            startActivity(intent);
        });

        button_load_products.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, ProductsActivity.class);
            startActivity(intent);
        });

        button_load_categories.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, CategoriesActivity.class);
            startActivity(intent);
        });

        button_load_dishes.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, DishesActivity.class);
            startActivity(intent);
        });

        button_load_settings.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
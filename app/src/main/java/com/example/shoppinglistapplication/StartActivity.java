package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.shoppinglistapplication.api.MainApiActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListActivity;

import java.util.prefs.Preferences;

public class StartActivity extends AppCompatActivity {

    private Button button_load_shopping_list;
    private Button button_load_list_of_preferences;
    private Button button_load_inspirations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme_Dark);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button_load_shopping_list = findViewById(R.id.button_load_shopping_list);
        button_load_list_of_preferences = findViewById(R.id.button_load_list);
        button_load_inspirations = findViewById(R.id.button_load_inspirations);

        button_load_list_of_preferences.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, ListsOfPreferencesActivity.class);
            startActivity(intent);
        });

        button_load_shopping_list.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, ShoppingListActivity.class);
            startActivity(intent);
        });

        button_load_inspirations.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, MainApiActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.book_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_products:
                intent = new Intent(StartActivity.this, ProductsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_categories:
                intent = new Intent(StartActivity.this, CategoriesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_dishes:
                intent = new Intent(StartActivity.this, DishesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_settings:
                intent = new Intent(StartActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
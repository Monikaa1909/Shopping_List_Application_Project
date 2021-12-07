package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.DishListAdapter;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;

public class NewListOfThePreferencesDetailActivity extends AppCompatActivity {

    private DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        int idListOfThePreferences = (int) getIntent().getSerializableExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishListAdapter adapter = new DishListAdapter(new DishListAdapter.DishDiff(), 4, idListOfThePreferences);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dishViewModel = new DishViewModel(this.getApplication());

        dishViewModel.getAllDishes().observe(this, dishes -> {
            adapter.submitList(dishes);
        });
    }
}
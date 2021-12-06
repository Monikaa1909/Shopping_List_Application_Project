package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;

import android.os.Bundle;
import android.util.Log;

public class NewDishDetailActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), 2, idDish);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = new CategoryViewModel(this.getApplication());

        categoryViewModel.getAllCategory().observe(this, categories -> {
            adapter.submitList(categories);
        });
    }
}
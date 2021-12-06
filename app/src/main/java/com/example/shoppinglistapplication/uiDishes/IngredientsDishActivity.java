package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IngredientsDishActivity extends AppCompatActivity {

    private static final String KEY_DISH_NAME = "dishName";
    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        new Thread(() -> {
            recyclerView = findViewById(R.id.recyclerview);
            final DishDetailAdapter adapter = new DishDetailAdapter(new DishDetailAdapter.DishDetailDiff());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            String dishName = getIntent().getExtras().getString(KEY_DISH_NAME);
            ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());

            List<DishDetail> dishDetails = ingredientsOfTheDishViewModel.getDishDetail(dishName);
            adapter.submitList(dishDetails);
        }).start();

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(IngredientsDishActivity.this, NewDishDetailActivity.class);
            startActivity(intent);
        });
    }
}
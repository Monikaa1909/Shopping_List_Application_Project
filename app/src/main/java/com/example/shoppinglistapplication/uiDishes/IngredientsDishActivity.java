package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToDeleteActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToEditActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IngredientsDishActivity extends AppCompatActivity {

    public static final String KEY_DISH_ID = "dishID2";
    public static final String KEY_CATEGORY_ID = "categoryID2";
    public static final String KEY_NEW_INGREDIENT_ID = "newIngredient2";
    public static final String KEY_NEW_INGREDIENT_UNIT = "newIngredientUnit2";

    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        new Thread(() -> {
            recyclerView = findViewById(R.id.recyclerview);
            final DishDetailAdapter adapter = new DishDetailAdapter(new DishDetailAdapter.DishDetailDiff(), 1);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            int idDish = (int) getIntent().getSerializableExtra(KEY_DISH_ID);
            ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());

            List<DishDetail> dishDetails = ingredientsOfTheDishViewModel.getDishDetail(idDish);
            adapter.submitList(dishDetails);

            FloatingActionButton fab = findViewById(R.id.fab_add);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(IngredientsDishActivity.this, NewDishDetailActivity.class);
                intent.putExtra(KEY_DISH_ID, idDish);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
            deleteFab.setOnClickListener(view -> {
                Intent intent = new Intent(IngredientsDishActivity.this, IngredientsDishToDeleteActivity.class);
                intent.putExtra(KEY_DISH_ID, idDish);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton editFab = findViewById(R.id.fab_edit);
            editFab.setOnClickListener(view -> {
                Intent intent = new Intent(IngredientsDishActivity.this, IngredientsDishToEditActivity.class);
                intent.putExtra(KEY_DISH_ID, idDish);
                startActivity(intent);
                this.finish();
            });
        }).start();
    }
}
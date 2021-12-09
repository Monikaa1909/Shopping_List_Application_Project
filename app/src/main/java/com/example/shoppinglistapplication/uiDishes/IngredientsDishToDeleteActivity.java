package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter;
import com.example.shoppinglistapplication.adapterholder.DishListAdapter;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.uiCategories.CategoriesToEditActivity;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IngredientsDishToDeleteActivity extends AppCompatActivity {

    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz składnik, który chcesz usunąć:");

        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);

        ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishDetailAdapter adapter = new DishDetailAdapter(new DishDetailAdapter.DishDetailDiff(), 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            List<DishDetail> dishDetails = ingredientsOfTheDishViewModel.getDishDetail(idDish);
            adapter.submitList(dishDetails);
        }).start();
    }
}
package com.example.shoppinglistapplication.uiDishes;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;

import java.util.List;

public class IngredientsDishToEditActivity extends AppCompatActivity {

    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz składnik, który chcesz edytować:");

        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);

        ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishDetailAdapter adapter = new DishDetailAdapter(new DishDetailAdapter.DishDetailDiff(), 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            List<DishDetail> dishDetails = ingredientsOfTheDishViewModel.getDishDetail(idDish);
            adapter.submitList(dishDetails);
        }).start();
    }
}
package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishToDeleteActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishToEditActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class OneListOfPreferencesActivity extends AppCompatActivity {

    private ListOfPreferencesViewModel listOfPreferencesViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String listOfPreferencesName = getIntent().getStringExtra(ListsOfPreferencesActivity.KEY_LIST_OF_PREFERENCES_NAME);
        setContentView(R.layout.recyclerview_with_all_button);

        new Thread(() -> {
            listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());
//            int id = listOfPreferencesViewModel.
//            recyclerView = findViewById(R.id.recyclerview);
//            final DishDetailAdapter adapter = new DishDetailAdapter(new DishDetailAdapter.DishDetailDiff(), 1);
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//            int idDish = (int) getIntent().getSerializableExtra(KEY_DISH_ID);
//            ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());
//
//            List<DishDetail> dishDetails = ingredientsOfTheDishViewModel.getDishDetail(idDish);
//            adapter.submitList(dishDetails);

            FloatingActionButton fab = findViewById(R.id.fab_add);
            fab.setOnClickListener(view -> {
//                Intent intent = new Intent(IngredientsDishActivity.this, NewDishDetailActivity.class);
//                intent.putExtra(KEY_DISH_ID, idDish);
//                startActivity(intent);
//                this.finish();
            });

            FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
            deleteFab.setOnClickListener(view -> {
//                Intent intent = new Intent(IngredientsDishActivity.this, IngredientsDishToDeleteActivity.class);
//                intent.putExtra(KEY_DISH_ID, idDish);
//                startActivity(intent);
//                this.finish();
            });

            FloatingActionButton editFab = findViewById(R.id.fab_edit);
            editFab.setOnClickListener(view -> {
//                Intent intent = new Intent(IngredientsDishActivity.this, IngredientsDishToEditActivity.class);
//                intent.putExtra(KEY_DISH_ID, idDish);
//                startActivity(intent);
//                this.finish();
            });
        }).start();

    }

}
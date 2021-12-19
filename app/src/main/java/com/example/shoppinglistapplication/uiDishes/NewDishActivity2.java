package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;

public class NewDishActivity2 extends AppCompatActivity {

    public static final String KEY_NEW_DISH_ID = "newDishID";
    public static final String KEY_NEW_INGREDIENT_CATEGORY_ID = "newIngredientCategoryID";
    public static final String KEY_NEW_INGREDIENT_ID = "newIngredientID";
    public static final String KEY_NEW_INGREDIENT_UNIT = "newIngredientUnit";
    private Button button_add_ingredients;
    private Button button_add_later;
    private DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_two_choices);

        button_add_ingredients = findViewById(R.id.button_first_choice);
        button_add_later = findViewById(R.id.button_second_choice);

        button_add_ingredients.setText(R.string.button_add_ingredients);
        button_add_later.setText(R.string.button_add_ingredients_later);

        dishViewModel = new DishViewModel(this.getApplication());

        new Thread(() -> {
            int idDish;
            if (getIntent().getStringExtra(NewDishActivity.KEY_NEW_DISH_NAME) != null) {
                String newDishName = getIntent().getStringExtra(NewDishActivity.KEY_NEW_DISH_NAME);
                idDish = dishViewModel.getIdDishByName(newDishName);
            } else {
                idDish = (int) getIntent().getSerializableExtra(NewDishActivity2.KEY_NEW_DISH_ID);
            }

            button_add_ingredients.setOnClickListener(view -> {
                Intent intent = new Intent(NewDishActivity2.this, NewDishActivity3.class);
                intent.putExtra(KEY_NEW_DISH_ID, idDish);
                startActivity(intent);
                finish();
            });

            button_add_later.setOnClickListener(view -> {
                Intent intent = new Intent(NewDishActivity2.this, DishesActivity.class);
                startActivity(intent);
                finish();
            });

        }).start();
    }
}
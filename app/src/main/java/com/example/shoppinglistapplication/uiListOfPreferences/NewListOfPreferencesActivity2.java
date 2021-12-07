package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity2;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity3;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;

public class NewListOfPreferencesActivity2 extends AppCompatActivity {

    public static final String KEY_NEW_LIST_OF_PREFERENCES_ID = "newListOfPreferencesID";
    public static final String KEY_NEW_DISH_IN_LIST_OF_PREFERENCES_ID = "newDishInListOfPreferencesID";
    private Button button_add_ingredients;
    private Button button_add_later;
    private ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_choices);

        button_add_ingredients = findViewById(R.id.button_add_ingredients);
        button_add_later = findViewById(R.id.button_add_ingredients_later);

        listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());

        new Thread(() -> {
            int idListOfPreferences;
            if (getIntent().getStringExtra(NewListOfPreferencesActivity.KEY_NEW_LIST_OF_PREFERENCES_NAME) != null) {
                String newListName = getIntent().getStringExtra(NewListOfPreferencesActivity.KEY_NEW_LIST_OF_PREFERENCES_NAME);
                idListOfPreferences = listOfPreferencesViewModel.getIdListOfPreferencesByName(newListName);
            } else {
                idListOfPreferences = (int) getIntent().getSerializableExtra(NewListOfPreferencesActivity2.KEY_NEW_LIST_OF_PREFERENCES_ID);
            }

            button_add_ingredients.setOnClickListener(view -> {
                Intent intent = new Intent(NewListOfPreferencesActivity2.this, NewListOfPreferencesActivity3.class);
                intent.putExtra(KEY_NEW_LIST_OF_PREFERENCES_ID, idListOfPreferences);
                startActivity(intent);
                finish();
            });

            button_add_later.setOnClickListener(view -> {
                Intent intent = new Intent(NewListOfPreferencesActivity2.this, ListsOfPreferencesActivity.class);
                startActivity(intent);
                finish();
            });

        }).start();
    }
}
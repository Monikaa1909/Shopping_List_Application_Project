package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.ICompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.SimpleCompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;

public class TypeOfShoppingListActivity extends AppCompatActivity {

    public static final String TYPE_OF_SHOPPING_LIST = "typeOfShoppingList";
    private Button button_simple_list;
    private Button button_optimized_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_choices);

        button_simple_list = findViewById(R.id.button_first_choice);
        button_optimized_list = findViewById(R.id.button_second_choice);

        button_simple_list.setText(R.string.button_create_simple_list);
        button_optimized_list.setText(R.string.button_create_optimized_list);

        int idListOfPreferences = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID);
        String listOfPreferencesName  = getIntent().getStringExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_NAME);

        button_simple_list.setOnClickListener(view -> {
            Intent intent = new Intent(TypeOfShoppingListActivity.this, NewShoppingListNameActivity.class);
            intent.putExtra(TYPE_OF_SHOPPING_LIST, "simple");
            intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID, idListOfPreferences);
            intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_NAME, listOfPreferencesName);
            startActivity(intent);
            finish();
        });

        button_optimized_list.setOnClickListener(view -> {
            Intent intent = new Intent(TypeOfShoppingListActivity.this, NewShoppingListNameActivity.class);
            intent.putExtra(TYPE_OF_SHOPPING_LIST, "optimized");
            intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID, idListOfPreferences);
            intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_NAME, listOfPreferencesName);
            startActivity(intent);
            finish();
        });
    }
}
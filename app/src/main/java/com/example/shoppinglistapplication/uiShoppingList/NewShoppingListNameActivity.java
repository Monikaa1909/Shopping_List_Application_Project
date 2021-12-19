package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

public class NewShoppingListNameActivity extends AppCompatActivity {

    private ShoppingListViewModel shoppingListViewModel;
    private EditText editListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editListName = findViewById(R.id.new_element_name);
        editListName.setHint(R.string.hint_new_shopping_list_name);

        int idListOfPreferences = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID);
        String listOfPreferencesName  = getIntent().getStringExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_NAME);
        String typeOfShoppingList  = getIntent().getStringExtra(TypeOfShoppingListActivity.TYPE_OF_SHOPPING_LIST);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            new Thread(() -> {
                String shoppingListName;
                if (TextUtils.isEmpty(editListName.getText())) {
                    shoppingListName = listOfPreferencesName;
                } else {
                    DataValidator validator = new DataValidator();
                    shoppingListName = validator.validateName(editListName.getText().toString());
                }
                shoppingListViewModel = new ShoppingListViewModel(this.getApplication());
                if (!shoppingListViewModel.shoppingListsExists(shoppingListName)) {
                    Intent intent = new Intent(NewShoppingListNameActivity.this, NewShoppingListDetailActivity.class);
                    ShoppingList shoppingList = new ShoppingList(shoppingListName, typeOfShoppingList);
                    shoppingListViewModel.insert(shoppingList, emptyFunction -> {});
                    intent.putExtra(TypeOfShoppingListActivity.TYPE_OF_SHOPPING_LIST, typeOfShoppingList);
                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_NAME, shoppingListName);
                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID, idListOfPreferences);
                    startActivity(intent);
                    this.finish();
                } else {
                    Intent intent = new Intent(NewShoppingListNameActivity.this, GenerateShoppingListActivity.class);
                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_INFO, "alreadyExists");
                    intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
                    startActivity(intent);
                    this.finish();
                }
            }).start();
        });




    }
}
package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity2;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

import java.util.List;

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

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            new Thread(() -> {
                String shoppingListName;
                if (TextUtils.isEmpty(editListName.getText())) {
                    Log.d("NAZWAPROBLEM", "pole puste, domyslna nazwa: " + listOfPreferencesName);
                    shoppingListName = listOfPreferencesName;
                } else {
                   shoppingListName = editListName.getText().toString();
                }
                shoppingListViewModel = new ShoppingListViewModel(this.getApplication());
                if (!shoppingListViewModel.shoppingListsExists(shoppingListName)) {
                    Log.d("NAZWAPROBLEM", "nazwa nie isnieje: " + shoppingListName);
                    Intent intent = new Intent(NewShoppingListNameActivity.this, ShoppingListDetailActivity.class);
                    ShoppingList shoppingList = new ShoppingList(shoppingListName);
                    shoppingListViewModel.insert(shoppingList, emptyFunction -> {});
                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_NAME, shoppingListName);
                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID, idListOfPreferences);
                    intent.putExtra(ShoppingListDetailActivity.REQUEST_CREATE_SHOPPING_LIST_DETAIL, "create");
                    startActivity(intent);
                    this.finish();
                } else {
                    Intent intent = new Intent(NewShoppingListNameActivity.this, GenerateShoppingListActivity.class);
                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_INFO, "alreadyExists");
                    intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
//                    intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_NAME, shoppingListName);
                    startActivity(intent);
                    this.finish();
                }
            }).start();
        });




    }
}
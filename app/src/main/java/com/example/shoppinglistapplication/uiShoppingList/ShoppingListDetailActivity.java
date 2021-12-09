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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ListOfThePreferenceDetailAdapter2;
import com.example.shoppinglistapplication.adapterholder.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.entity.CompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

import java.util.List;

public class ShoppingListDetailActivity extends AppCompatActivity {

    public static final String REQUEST_CREATE_SHOPPING_LIST_DETAIL = "createShoppingListDetail";
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Skład listy zakupów");

        if (getIntent().getStringExtra(REQUEST_CREATE_SHOPPING_LIST_DETAIL) != null) {
            String shoppingListName = getIntent().getStringExtra(ShoppingListActivity.KEY_SHOPPING_LIST_NAME);
            String request = getIntent().getStringExtra(REQUEST_CREATE_SHOPPING_LIST_DETAIL);
            int idListOfPreferences = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID);
            if (request.equals("create")) {
                new Thread(() -> {
                    RecyclerView recyclerView = findViewById(R.id.recyclerview);
                    final ShoppingListDetailAdapter adapter = new ShoppingListDetailAdapter(new ShoppingListDetailAdapter.ShoppingListDetailDiff());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));

                    CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(this.getApplication());
                    int idShoppingList = compositionOfTheShoppingListViewModel.getIdShoppingListByName(shoppingListName);
                    List<ShoppingListDetail> shoppingListDetails = compositionOfTheShoppingListViewModel.getShoppingListDetailByPreferencesListId(idListOfPreferences);
                    for (ShoppingListDetail detail : shoppingListDetails) {
                        CompositionOfTheShoppingList compositionOfTheShoppingList = new CompositionOfTheShoppingList(detail.getQuantity(), idShoppingList, detail.getIdProduct());
                        compositionOfTheShoppingListViewModel.insert(compositionOfTheShoppingList, emptyFunction -> {});
                    }
                    adapter.submitList(shoppingListDetails);
                }).start();
            }
        } else {
            int idShoppingList = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID);
            new Thread(() -> {
                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                final ShoppingListDetailAdapter adapter = new ShoppingListDetailAdapter(new ShoppingListDetailAdapter.ShoppingListDetailDiff());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(this.getApplication());
                List<ShoppingListDetail> shoppingListDetails = compositionOfTheShoppingListViewModel.getShoppingListDetailByShoppingListId(idShoppingList);
                for (ShoppingListDetail detail : shoppingListDetails) {
                    CompositionOfTheShoppingList compositionOfTheShoppingList = new CompositionOfTheShoppingList(detail.getQuantity(), idShoppingList, detail.getIdProduct());
                    compositionOfTheShoppingListViewModel.insert(compositionOfTheShoppingList, emptyFunction -> {});
                }
                adapter.submitList(shoppingListDetails);
            }).start();
        }
    }
}
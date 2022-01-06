package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.StartActivity;
import com.example.shoppinglistapplication.adapter.OptimizedShoppingListDetailAdapter;
import com.example.shoppinglistapplication.adapter.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.OptimizedCompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.SimpleCompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.entity.OptimizedCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.helpfulModel.OptimizedShoppingListDetail;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewShoppingListDetailActivity extends AppCompatActivity {

    public static final String REQUEST_CREATE_SHOPPING_LIST_DETAIL = "createShoppingListDetail";
    private Button button_show_list;
    private Button button_show_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_choices);

        button_show_list = findViewById(R.id.button_first_choice);
        button_show_menu = findViewById(R.id.button_second_choice);

        button_show_list.setText(R.string.button_show_list);
        button_show_menu.setText(R.string.button_show_menu);

        String shoppingListName = getIntent().getStringExtra(ShoppingListActivity.KEY_SHOPPING_LIST_NAME);
        String request = getIntent().getStringExtra(REQUEST_CREATE_SHOPPING_LIST_DETAIL);
        String typeOfShoppingList = getIntent().getStringExtra(TypeOfShoppingListActivity.TYPE_OF_SHOPPING_LIST);

        int idListOfPreferences = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID);

        new Thread(() -> {
            CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(this.getApplication());
            int idShoppingList = compositionOfTheShoppingListViewModel.getIdShoppingListByName(shoppingListName);
            List<ShoppingListDetail> shoppingListDetails = compositionOfTheShoppingListViewModel.getShoppingListDetailByPreferencesListId(idListOfPreferences);

            if (typeOfShoppingList.equals("simple")) {
                for (ShoppingListDetail detail : shoppingListDetails) {

                    // WZORZEC BUILDER:
                    SimpleCompositionOfTheShoppingListBuilder simpleCompositionBuilder = SimpleCompositionOfTheShoppingListBuilder.getInstance();
                    simpleCompositionBuilder.setQuantity(detail.getQuantity());
                    simpleCompositionBuilder.setIdShoppingList(idShoppingList);
                    simpleCompositionBuilder.setIdProduct(detail.getIdProduct());
                    simpleCompositionBuilder.setSuggestedFormOfAccessibility(detail.getQuantity(), null, null);
                    SimpleCompositionOfTheShoppingList simpleCompositionOfTheShoppingList = simpleCompositionBuilder.getResult();
                    compositionOfTheShoppingListViewModel.insert(simpleCompositionOfTheShoppingList, emptyFunction -> {});
                }
            } else {
                for (ShoppingListDetail detail : shoppingListDetails) {

                    // WZORZEC BUILDER:
                    OptimizedCompositionOfTheShoppingListBuilder optimizedCompositionBuilder = OptimizedCompositionOfTheShoppingListBuilder.getInstance();
                    optimizedCompositionBuilder.setQuantity(detail.getQuantity());
                    optimizedCompositionBuilder.setIdShoppingList(idShoppingList);
                    optimizedCompositionBuilder.setIdProduct(detail.getIdProduct());

                    List<Double> forms = compositionOfTheShoppingListViewModel.getFormsOfAccessibilityByProductId(detail.getIdProduct());
                    String unit = compositionOfTheShoppingListViewModel.getUnitOfProduct(detail.getIdProduct());
                    optimizedCompositionBuilder.setSuggestedFormOfAccessibility(detail.getQuantity(), forms, unit);


                    OptimizedCompositionOfTheShoppingList optimizedCompositionOfTheShoppingList = optimizedCompositionBuilder.getResult();
                    compositionOfTheShoppingListViewModel.insert(optimizedCompositionOfTheShoppingList, emptyFunction -> {});
                }
            }

            button_show_list.setOnClickListener(view -> {
                Intent intent = new Intent(NewShoppingListDetailActivity.this, ShoppingListDetailActivity.class);
                intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID, idShoppingList);
                intent.putExtra(ShoppingListDetailActivity.THEME_TYPE, 0);
                startActivity(intent);
                finish();
            });

            button_show_menu.setOnClickListener(view -> {
                Intent intent = new Intent(NewShoppingListDetailActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            });
        }).start();
    }
}
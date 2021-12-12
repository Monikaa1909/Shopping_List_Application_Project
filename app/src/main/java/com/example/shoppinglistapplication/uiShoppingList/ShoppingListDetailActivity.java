package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.SimpleCompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;

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

                        // WZORZEC BUILDER:
                        SimpleCompositionOfTheShoppingListBuilder simpleCompositionBuilder = new SimpleCompositionOfTheShoppingListBuilder();
                        simpleCompositionBuilder.setQuantity(detail.getQuantity());
                        simpleCompositionBuilder.setIdShoppingList(idShoppingList);
                        simpleCompositionBuilder.setIdProduct(detail.getIdProduct());
                        SimpleCompositionOfTheShoppingList simpleCompositionOfTheShoppingList = simpleCompositionBuilder.getResult();
//                        CompositionOfTheShoppingList compositionOfTheShoppingList = new CompositionOfTheShoppingList(detail.getQuantity(), idShoppingList, detail.getIdProduct(), "none");
                        compositionOfTheShoppingListViewModel.insert(simpleCompositionOfTheShoppingList, emptyFunction -> {});
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
//                for (ShoppingListDetail detail : shoppingListDetails) {
//                    CompositionOfTheShoppingList compositionOfTheShoppingList = new CompositionOfTheShoppingList(detail.getQuantity(), idShoppingList, detail.getIdProduct());
//                    compositionOfTheShoppingListViewModel.insert(compositionOfTheShoppingList, emptyFunction -> {});
//                }
                adapter.submitList(shoppingListDetails);
            }).start();
        }
    }
}
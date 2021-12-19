package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.adapter.OptimizedShoppingListDetailAdapter;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.OptimizedCompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder.SimpleCompositionOfTheShoppingListBuilder;
import com.example.shoppinglistapplication.entity.OptimizedCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.OptimizedShoppingListDetail;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDetailActivity extends AppCompatActivity {

    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Skład listy zakupów");

        int idShoppingList = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID);
        new Thread(() -> {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(this.getApplication());
            String typeOfShoppingList = compositionOfTheShoppingListViewModel.getTypeOfShoppingListById(idShoppingList);
            if (typeOfShoppingList.equals("simple")) {
                final ShoppingListDetailAdapter adapter = new ShoppingListDetailAdapter(new ShoppingListDetailAdapter.ShoppingListDetailDiff());
                recyclerView.setAdapter(adapter);
                List<ShoppingListDetail> shoppingListDetails = compositionOfTheShoppingListViewModel.getShoppingListDetailByShoppingListId(idShoppingList);
                adapter.submitList(shoppingListDetails);
            } else {
                final OptimizedShoppingListDetailAdapter adapter = new OptimizedShoppingListDetailAdapter(new OptimizedShoppingListDetailAdapter.ShoppingListDetailWithSuggestedFormDiff());
                recyclerView.setAdapter(adapter);
                List<OptimizedShoppingListDetail> shoppingListDetails = compositionOfTheShoppingListViewModel.getOptimizedShoppingListDetailByShoppingListId(idShoppingList);
                adapter.submitList(shoppingListDetails);
            }
        }).start();
    }
}
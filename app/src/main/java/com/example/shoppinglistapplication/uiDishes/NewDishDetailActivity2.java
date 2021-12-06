package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ProductListAdapter2;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import java.util.List;

public class NewDishDetailActivity2 extends AppCompatActivity {

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        int idCategory = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_CATEGORY_ID);
        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter2 adapter = new ProductListAdapter2(new ProductListAdapter2.ProductDiff(), 6, idDish);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ProductViewModel(this.getApplication());

        new Thread(() -> {
            List<Product> productsList = productViewModel.getAlphabetizedProductsByCategory(idCategory);
            adapter.submitList(productsList);
        }).start();
    }
}
package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.ItemState.ProductState.AddProductToDishState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.ProductListAdapter;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import java.util.List;

public class NewDishActivity4 extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz nowy składnik:");

        int idCategory = (int) getIntent().getSerializableExtra(NewDishActivity2.KEY_NEW_INGREDIENT_CATEGORY_ID);
        int idDish = (int) getIntent().getSerializableExtra(NewDishActivity2.KEY_NEW_DISH_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff(), new AddProductToDishState(idDish));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());
            List<Product> productsList = productViewModel.getAlphabetizedProductsByCategory(idCategory);
            adapter.submitList(productsList);
        }).start();

    }
}
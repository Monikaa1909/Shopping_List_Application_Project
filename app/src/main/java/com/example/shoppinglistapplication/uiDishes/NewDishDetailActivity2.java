package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.itemState.productState.AddProductToDishState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.ProductListAdapter;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import java.util.List;

public class NewDishDetailActivity2 extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz nowy składnik");

        int idCategory = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_CATEGORY_ID);
        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff(), new AddProductToDishState(idDish));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ProductViewModel(this.getApplication());

        new Thread(() -> {
            List<Product> productsList = productViewModel.getAlphabetizedProductsByCategory(idCategory);
            adapter.submitList(productsList);
        }).start();
    }
}
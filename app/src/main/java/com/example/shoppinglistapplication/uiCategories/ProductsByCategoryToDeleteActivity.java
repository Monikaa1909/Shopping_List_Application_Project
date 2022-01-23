package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.itemState.productState.ProductByCategoryToDeleteState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.ProductListAdapter;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import java.util.List;

public class ProductsByCategoryToDeleteActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz protukt, który chcesz usunąć:");

        int idCategory = (int) getIntent().getSerializableExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff(), new ProductByCategoryToDeleteState(idCategory));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());
            List<Product> productsList = productViewModel.getAlphabetizedProductsByCategory(idCategory);
            adapter.submitList(productsList);
        }).start();
    }
}
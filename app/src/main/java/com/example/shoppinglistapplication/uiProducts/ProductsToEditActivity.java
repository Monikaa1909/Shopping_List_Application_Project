package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.itemState.productState.ProductToEditState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.ProductListAdapter;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class ProductsToEditActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz produkt, który chcesz edytować:");

        productViewModel = new ProductViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff(), new ProductToEditState());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel.getAllProducts().observe(this, products -> {
            adapter.submitList(products);
        });
    }
}
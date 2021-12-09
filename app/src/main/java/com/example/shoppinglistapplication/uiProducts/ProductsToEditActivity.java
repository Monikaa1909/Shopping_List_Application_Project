package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ProductListAdapter2;
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
        final ProductListAdapter2 adapter = new ProductListAdapter2(new ProductListAdapter2.ProductDiff(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel.getAllProducts().observe(this, products -> {
            adapter.submitList(products);
        });
    }
}
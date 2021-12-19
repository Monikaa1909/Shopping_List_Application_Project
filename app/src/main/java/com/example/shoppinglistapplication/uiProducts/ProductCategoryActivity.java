package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.ItemState.CategoryState.CategoryNoActionState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.CategoryListAdapter;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ProductCategoryActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_edit_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Kategoria produktu:");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), new CategoryNoActionState());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int idProduct = (int) getIntent().getSerializableExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID);

        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());
            FloatingActionButton fab = findViewById(R.id.fab_edit);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(ProductCategoryActivity.this, EditProductCategoryActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                startActivity(intent);
                this.finish();
            });

            List<Category> categoriesList = productViewModel.getProductCategory(idProduct);
            adapter.submitList(categoriesList);
        }).start();
    }
}
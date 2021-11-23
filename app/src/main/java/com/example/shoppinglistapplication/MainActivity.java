package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shoppinglistapplication.entities.Category;
import com.example.shoppinglistapplication.entities.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private CategoryViewModel categoryViewModel;

    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
//        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        productViewModel.getAllProducts().observe(this, products -> {
            adapter.submitList(products);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
            startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String cat = data.getStringExtra(NewProductActivity.EXTRA_REPLY_CATEGORY);    // nazwa kategorii

            CategoryViewModelFactory factory = new CategoryViewModelFactory(this.getApplication(), cat, 1);
            categoryViewModel = new ViewModelProvider(this, factory).get(CategoryViewModel.class);

            System.out.println(cat);
            System.out.println(categoryViewModel.getCategory_name_by_id(1));
            Category category = new Category(cat);
            categoryViewModel.insert(category);
            Product product = new Product(data.getStringExtra(NewProductActivity.EXTRA_REPLY_NAME), 1,1,1);
            productViewModel.insert(product);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
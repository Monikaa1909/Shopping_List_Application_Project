package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoppinglistapplication.adapterholder.ProductListAdapter;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModelFactory;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductsActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private CategoryViewModel categoryViewModel;

    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductViewModelFactory factoryP = new ProductViewModelFactory(this.getApplication(), null, 0);
        productViewModel = new ViewModelProvider(this, factoryP).get(ProductViewModel.class);

        productViewModel.getAllProducts().observe(this, products -> {
            adapter.submitList(products);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, NewProductActivity.class);
            startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String categoryName = data.getStringExtra(NewProductActivity.EXTRA_REPLY_CATEGORY);

            new Thread(() -> {
                CategoryViewModelFactory factory = new CategoryViewModelFactory(this.getApplication(), categoryName, 1);
                categoryViewModel = new ViewModelProvider(this, factory).get(CategoryViewModel.class);

                if (!categoryViewModel.categoryExists(categoryName)) {
                    Category category = new Category(categoryName);
                    categoryViewModel.insert(category, id -> createProduct(data, (int) id));
                } else {
                    int categoryId = categoryViewModel.getIdCategoryByName(categoryName);
                    createProduct(data, categoryId);
                }
//                Log.d("CAT", String.valueOf(categoryViewModel.getCategory_name_by_id(1)));
            }).start();

        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void createProduct(Intent data, int categoryId) {
        Product product = new Product(data.getStringExtra(NewProductActivity.EXTRA_REPLY_NAME), categoryId,1,1);
        productViewModel.insert(product, emptyFunction -> {});
    }
}
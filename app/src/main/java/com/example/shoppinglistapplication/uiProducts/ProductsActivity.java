package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ProductListAdapter2;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductsActivity extends AppCompatActivity {

    public static final String KEY_PRODUCT_INFO = "productInfo";
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        if (getIntent().getStringExtra(KEY_PRODUCT_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_PRODUCT_INFO);
            if (info.equals("alreadyExists")) {
                Toast.makeText(getApplicationContext(),"Produkt o podanej nazwie już istnieje",Toast.LENGTH_LONG).show();
            }
        }

        productViewModel = new ProductViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter2 adapter = new ProductListAdapter2(new ProductListAdapter2.ProductDiff(), 3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel.getAllProducts().observe(this, products -> {
            adapter.submitList(products);
        });

        FloatingActionButton addFab = findViewById(R.id.fab_add);
        addFab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, NewProductActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, ProductsToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, ProductsToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            String categoryName = data.getStringExtra(NewProductActivity.EXTRA_REPLY_CATEGORY);
//
//            new Thread(() -> {
//                CategoryViewModelFactory factory = new CategoryViewModelFactory(this.getApplication(), categoryName, 1);
//                categoryViewModel = new ViewModelProvider(this, factory).get(CategoryViewModel.class);
//
//                if (!categoryViewModel.categoryExists(categoryName)) {
//                    Category category = new Category(categoryName);
//                    categoryViewModel.insert(category, id -> createProduct(data, (int) id));
//                } else {
//                    int categoryId = categoryViewModel.getIdCategoryByName(categoryName);
//                    createProduct(data, categoryId);
//                }
////                Log.d("CAT", String.valueOf(categoryViewModel.getCategory_name_by_id(1)));
//            }).start();
//
//        } else {
//            Toast.makeText(getApplicationContext(), R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
    }
}
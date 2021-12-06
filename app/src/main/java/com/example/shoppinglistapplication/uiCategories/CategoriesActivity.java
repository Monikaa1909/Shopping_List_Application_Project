package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsToDeleteActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsToEditActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoriesActivity extends AppCompatActivity {

    public static final String KEY_CATEGORY_INFO = "categoryInfo";
    private static final int NEW_CATEGORY_ACTIVITY_REQUEST_CODE = 1;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

//        if (getIntent().getStringExtra(KEY_CATEGORY_INFO) != null) {
//            String info = getIntent().getStringExtra(KEY_CATEGORY_INFO);
//            if (info.equals("alreadyExists")) {
//                Toast.makeText(getApplicationContext(),"Produkt o podanej nazwie już istnieje",Toast.LENGTH_LONG).show();
//            }
//        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = new CategoryViewModel(this.getApplication());
        categoryViewModel.getAllCategory().observe(this, categories -> {
            adapter.submitList(categories);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, NewCategoryActivity.class);
            startActivityForResult(intent, NEW_CATEGORY_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, CategoriesToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, CategoriesToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == NEW_CATEGORY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String categoryName = data.getStringExtra(NewCategoryActivity.EXTRA_REPLY_NAME);

            new Thread(() -> {
                categoryViewModel = new CategoryViewModel(this.getApplication());

                if (!categoryViewModel.categoryExists(categoryName)) {
                    Category category = new Category(categoryName);
                    categoryViewModel.insert(category, emptyFunction -> {});
                }
            }).start();

        } else {
        }
    }
}
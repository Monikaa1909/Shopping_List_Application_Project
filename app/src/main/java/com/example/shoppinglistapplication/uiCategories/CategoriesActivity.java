package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsToDeleteActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsToEditActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoriesActivity extends AppCompatActivity {

    public static final String KEY_CATEGORY_INFO = "categoryInfo";
//    private static final int NEW_CATEGORY_ACTIVITY_REQUEST_CODE = 1;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        if (getIntent().getStringExtra(KEY_CATEGORY_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_CATEGORY_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.category_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(CategoriesActivity.this, NewCategoryActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowej kategorii", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej kategorii",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

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
            startActivity(intent);
            this.finish();
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (requestCode == NEW_CATEGORY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            String categoryName = data.getStringExtra(NewCategoryActivity.EXTRA_REPLY_NAME);
//
//            new Thread(() -> {
//                categoryViewModel = new CategoryViewModel(this.getApplication());
//
//                if (!categoryViewModel.categoryExists(categoryName)) {
//                    Category category = new Category(categoryName);
//                    categoryViewModel.insert(category, emptyFunction -> {});
//                }
//            }).start();
//
//        } else {
//        }
//    }
}
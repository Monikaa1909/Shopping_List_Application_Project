package com.example.shoppinglistapplication.uiDishes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishListAdapter;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToDeleteActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToEditActivity;
import com.example.shoppinglistapplication.uiCategories.NewCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DishesActivity  extends AppCompatActivity {

    public static final String KEY_DISH_INFO = "dishInfo";
    private DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        if (getIntent().getStringExtra(KEY_DISH_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_DISH_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.dish_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(DishesActivity.this, NewDishActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowego dania", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego dania",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishListAdapter adapter = new DishListAdapter(new DishListAdapter.DishDiff(), 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dishViewModel = new DishViewModel(this.getApplication());

        dishViewModel.getAllDishes().observe(this, dishes -> {
            adapter.submitList(dishes);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(DishesActivity.this, NewDishActivity.class);
            startActivity(intent);
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(DishesActivity.this, DishesToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(DishesActivity.this, DishesToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == NEW_CATEGORY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            String categoryName = data.getStringExtra(NewCategoryActivity.EXTRA_REPLY_NAME);
//
//            new Thread(() -> {
//                CategoryViewModelFactory factory = new CategoryViewModelFactory(this.getApplication(), categoryName, 0);
//                categoryViewModel = new ViewModelProvider(this, factory).get(CategoryViewModel.class);
//
//                if (!categoryViewModel.categoryExists(categoryName)) {
//                    Category category = new Category(categoryName);
//                    categoryViewModel.insert(category, emptyFunction -> {});
//                }
//            }).start();
//
//        } else {
//            Toast.makeText(getApplicationContext(), R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
//    }
}

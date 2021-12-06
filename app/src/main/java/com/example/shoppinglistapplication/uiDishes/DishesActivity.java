package com.example.shoppinglistapplication.uiDishes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishListAdapter;
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
                Toast.makeText(getApplicationContext(),"Danie o podanej nazwie już istnieje",Toast.LENGTH_LONG).show();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishListAdapter adapter = new DishListAdapter(new DishListAdapter.DishDiff());
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

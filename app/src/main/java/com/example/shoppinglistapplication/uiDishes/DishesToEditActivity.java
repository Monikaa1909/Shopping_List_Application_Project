package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.DishListAdapter;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;

public class DishesToEditActivity extends AppCompatActivity {

    private DishViewModel dishViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz danie, które chcesz edytować:");

        dishViewModel = new DishViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishListAdapter adapter = new DishListAdapter(new DishListAdapter.DishDiff(), 3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dishViewModel = new DishViewModel(this.getApplication());
        dishViewModel.getAllDishes().observe(this, dishes -> {
            adapter.submitList(dishes);
        });
    }
}
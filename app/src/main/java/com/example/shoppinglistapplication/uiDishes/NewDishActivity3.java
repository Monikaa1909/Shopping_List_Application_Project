package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.itemState.categoryState.AddCategoryToIngredientsNewDishState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.CategoryListAdapter;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;

public class NewDishActivity3 extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz kategorię nowego składnika:");

        int newDishId = (int) getIntent().getSerializableExtra(NewDishActivity2.KEY_NEW_DISH_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), new AddCategoryToIngredientsNewDishState(newDishId));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = new CategoryViewModel(this.getApplication());
        categoryViewModel.getAllCategory().observe(this, categories -> {
            adapter.submitList(categories);
        });
    }
}
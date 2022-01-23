package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.itemState.categoryState.AddCategoryToIngredientState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.CategoryListAdapter;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;

import android.os.Bundle;
import android.widget.TextView;

public class NewDishDetailActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz kategorię nowego składnika:");

        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), new AddCategoryToIngredientState(idDish));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = new CategoryViewModel(this.getApplication());

        categoryViewModel.getAllCategory().observe(this, categories -> {
            adapter.submitList(categories);
        });
    }
}
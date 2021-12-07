package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.PreferencesListAdapter;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToDeleteActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToEditActivity;
import com.example.shoppinglistapplication.uiCategories.NewCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListsOfPreferencesActivity extends AppCompatActivity {

    public static final String KEY_LIST_OF_PREFERENCES_NAME = "listOfPreferencesName";
    private ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PreferencesListAdapter adapter = new PreferencesListAdapter(new PreferencesListAdapter.PreferencesDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());
        listOfPreferencesViewModel.getListOfPreferences().observe(this, preferences -> {
            adapter.submitList(preferences);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListsOfPreferencesActivity.this, NewListOfPreferencesActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
//            Intent intent = new Intent(ListsOfPreferencesActivity.this, CategoriesToDeleteActivity.class);
//            startActivity(intent);
//            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
//            Intent intent = new Intent(ListsOfPreferencesActivity.this, CategoriesToEditActivity.class);
//            startActivity(intent);
//            this.finish();
        });
    }

}
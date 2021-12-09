package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.PreferencesListAdapter;
import com.example.shoppinglistapplication.adapterholder.ShoppingListAdapter;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

public class ShoppingListActivityToEditActivity extends AppCompatActivity {

    private ShoppingListViewModel shoppingListViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz listę, którą chcesz edytować:");

        shoppingListViewModel = new ShoppingListViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ShoppingListAdapter adapter = new ShoppingListAdapter(new ShoppingListAdapter.ShoppingListDiff(), 3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingListViewModel.getShoppingList().observe(this, shoppingLists -> {
            adapter.submitList(shoppingLists);
        });
    }
}
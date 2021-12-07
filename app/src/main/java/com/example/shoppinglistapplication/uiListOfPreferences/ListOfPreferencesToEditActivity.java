package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.PreferencesListAdapter;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;

public class ListOfPreferencesToEditActivity extends AppCompatActivity {

    private ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PreferencesListAdapter adapter = new PreferencesListAdapter(new PreferencesListAdapter.PreferencesDiff(), 3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfPreferencesViewModel.getListOfPreferences().observe(this, preferences -> {
            adapter.submitList(preferences);
        });
    }
}
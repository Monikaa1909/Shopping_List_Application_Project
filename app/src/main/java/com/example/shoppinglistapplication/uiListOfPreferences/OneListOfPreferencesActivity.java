package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;

public class OneListOfPreferencesActivity extends AppCompatActivity {

    private ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_list_of_preferences);
        String listOfPreferencesName = getIntent().getStringExtra("listOfPreferencesName");


//        ListOfPreferencesViewModelFactory factory = new ListOfPreferencesViewModelFactory(this.getApplication(), listOfPreferencesName, 0);
//        listOfPreferencesViewModel = new ViewModelProvider(this, factory).get(ListOfPreferencesViewModel.class);
//        ListOfPreferences listOfPreferences = new ListOfPreferences(listOfPreferencesName);
//        listOfPreferencesViewModel.insert(listOfPreferences);

        new Thread(() -> {
            listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());

            if (!listOfPreferencesViewModel.listOfPreferencesExists(listOfPreferencesName)) {
                ListOfPreferences listOfPreferences = new ListOfPreferences(listOfPreferencesName);
                listOfPreferencesViewModel.insert(listOfPreferences);
            }
        }).start();
    }

}
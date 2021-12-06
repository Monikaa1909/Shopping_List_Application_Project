package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.ListOfPreferences;

import java.util.List;

public class ListOfPreferencesViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<ListOfPreferences>> listOfPreferences;

    private LiveData<List<Category>> categoriesById;
    private Integer categoriesById2;
    private LiveData<String> categoryNameById;

    public ListOfPreferencesViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        listOfPreferences = dataRepository.getListsOfPreferences();
    }

    public LiveData<List<ListOfPreferences>> getListOfPreferences() {
        return listOfPreferences;
    }

    public void insert(ListOfPreferences list) {
        dataRepository.insert(list);
    }

    public Boolean listOfPreferencesExists(String name) {
        return dataRepository.listOfPreferencesExists(name);
    }
}

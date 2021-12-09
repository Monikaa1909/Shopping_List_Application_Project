package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

public class ListOfPreferencesViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<ListOfPreferences>> listOfPreferences;

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

    public List<ShoppingListDetail> getShoppingListDetail(int idListOfPreferences) {return dataRepository.getShoppingListDetail(idListOfPreferences); }

    public Boolean listOfPreferencesExists(String name) {
        return dataRepository.listOfPreferencesExists(name);
    }

    public Integer getIdListOfPreferencesByName(String name) {
        return dataRepository.getIdByListOfPreferencesName(name); }

    public String getNameByListOfPreferencesId(int id) {
        return dataRepository.getNameByListOfPreferencesId(id);
    }

    public void deleteListOfPreferencesById(int id, DataRepository.Executor executor) {
        dataRepository.deleteListOfPreferences(id);
    }

    public void updateListOfPreferencesName(int id, String newName, DataRepository.Executor executor) {
        dataRepository.updateListOfPreferences(id, newName);
    }
}

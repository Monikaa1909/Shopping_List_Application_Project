package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

public class ShoppingListViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<ShoppingList>> shoppingList;

    public ShoppingListViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        shoppingList = dataRepository.getAllShoppingList();
    }

    public LiveData<List<ShoppingList>> getShoppingList() {
        return shoppingList;
    }

    public void insert(ShoppingList shoppingList, DataRepository.Executor executor) {
        dataRepository.insert(shoppingList, executor);
    }

    public List<ShoppingListDetail> getShoppingListDetail(int idListOfPreferences) {return dataRepository.getShoppingListDetail2(idListOfPreferences); }

    public Boolean shoppingListsExists(String name) {
        return dataRepository.shoppingListExists(name);
    }

    public Integer getIdShoppingListByName(String name) {
        return dataRepository.getIdByShoppingListName(name); }

    public void deleteShoppingListById(int id, DataRepository.Executor executor) {
        dataRepository.deleteShoppingList(id);
    }

    public void updateShoppingListName(int id, String newName, DataRepository.Executor executor) {
        dataRepository.updateShoppingList(id, newName);
    }
}
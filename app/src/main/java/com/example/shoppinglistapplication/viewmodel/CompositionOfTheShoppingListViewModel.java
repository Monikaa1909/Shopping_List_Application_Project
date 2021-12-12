package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

public class CompositionOfTheShoppingListViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<SimpleCompositionOfTheShoppingList>> compositionOfTheShoppingList;

    public CompositionOfTheShoppingListViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        compositionOfTheShoppingList = dataRepository.getCompositionOfTheShoppingList();
    }

    public LiveData<List<SimpleCompositionOfTheShoppingList>> getAllCompositionOfTheShoppingList() {
        return compositionOfTheShoppingList;
    }

    public List<ShoppingListDetail> getShoppingListDetailByPreferencesListId(int idListOfPreferences) {return dataRepository.getShoppingListDetailByPreferencesListId(idListOfPreferences); }

    public List<ShoppingListDetail> getShoppingListDetailByShoppingListId(int idShoppingList) { return dataRepository.getShoppingListDetailByShoppingListId(idShoppingList);}

    public Integer getIdShoppingListByName(String name) {
        return dataRepository.getIdByShoppingListName(name); }

    public void insert(SimpleCompositionOfTheShoppingList simpleCompositionOfTheShoppingList, DataRepository.Executor executor) {
        dataRepository.insert(simpleCompositionOfTheShoppingList, executor);
    }
}

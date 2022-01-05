package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.OptimizedCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListCheckBox;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.helpfulModel.OptimizedShoppingListDetail;

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

    public List<OptimizedShoppingListDetail> getOptimizedShoppingListDetailByShoppingListId(int idShoppingList) { return dataRepository.getOptimizedShoppingListDetailByShoppingListId(idShoppingList);}

    public List<ShoppingListCheckBox> getShoppingListCheckBoxByShoppingListId(int idShoppingList) { return dataRepository.getShoppingListCheckBoxByShoppingListId(idShoppingList);}

    public List<ShoppingListCheckBox> getOptimizedShoppingListCheckBoxByShoppingListId(int idShoppingList) { return dataRepository.getOptimizedShoppingListCheckBoxByShoppingListId(idShoppingList);}

    public String getTypeOfShoppingListById(int idShoppingList) { return dataRepository.getTypeOfShoppingListById(idShoppingList); }

    public List<Double> getFormsOfAccessibilityByProductId(int idProduct) { return dataRepository.getFormsOfAccessibilityByProductId(idProduct); }

    public Integer getIdShoppingListByName(String name) { return dataRepository.getIdByShoppingListName2(name); }

    public String getUnitOfProduct(int idProduct) { return dataRepository.getUnitOfProduct(idProduct); }

    public void updateSimpleShoppingListBought(long idShoppingList, long idProduct, Boolean bought, DataRepository.Executor executor) {
        dataRepository.updateSimpleShoppingListBought(idShoppingList, idProduct, bought);
    }

    public void updateOptimizedShoppingListBought(long idShoppingList, long idProduct, Boolean bought, DataRepository.Executor executor) {
        dataRepository.updateOptimizedShoppingListBought(idShoppingList, idProduct, bought);
    }

    public void insert(SimpleCompositionOfTheShoppingList simpleCompositionOfTheShoppingList, DataRepository.Executor executor) {
        dataRepository.insert(simpleCompositionOfTheShoppingList, executor);
    }

    public void insert(OptimizedCompositionOfTheShoppingList optimizedCompositionOfTheShoppingList, DataRepository.Executor executor) {
        dataRepository.insert(optimizedCompositionOfTheShoppingList, executor);
    }
}

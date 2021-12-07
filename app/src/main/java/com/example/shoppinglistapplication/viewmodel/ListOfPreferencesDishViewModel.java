package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;

import java.util.List;

public class ListOfPreferencesDishViewModel extends AndroidViewModel {

    private DataRepository dataRepository;
    private LiveData<List<ListOfPreferencesDish>> listOfPreferencesDish;

    public ListOfPreferencesDishViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        listOfPreferencesDish = dataRepository.getAllListOfPreferencesDish();
    }

    public LiveData<List<ListOfPreferencesDish>> getListOfPreferencesDish() { return listOfPreferencesDish; }

    public List<ListOfPreferencesDetail> getListOfPreferencesDishDetail(int idListOfPreferences) { return dataRepository.getListOfPreferencesDishDetail(idListOfPreferences); }

    public Boolean listOfPreferencesDishExists(int idDish, int idListOfPreferences) {
        return dataRepository.listOfPreferencesDishExists(idDish, idListOfPreferences);
    }

    public void insert(ListOfPreferencesDish listOfPreferencesDish, DataRepository.Executor executor) {
        dataRepository.insert(listOfPreferencesDish, executor);
    }

    public void deleteListOfPreferencesDish(int idDish, int idListOfPreferences, DataRepository.Executor executor) {
        dataRepository.deleteListOfPreferencesDish(idDish, idListOfPreferences);
    }

    public void updateListOfPreferencesDishPortions(int idDish, int idListOfPreferences, int portions, DataRepository.Executor executor) {
        dataRepository.updateListOfPreferencesDishPortions(idDish, idListOfPreferences, portions);
    }
}
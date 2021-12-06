package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;

import java.util.List;

public class IngredientsOfTheDishViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<IngredientsOfTheDish>> ingredientsOfTheDish;

    public IngredientsOfTheDishViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        ingredientsOfTheDish = dataRepository.getAllIngredientsOfTheDish();
    }

    public LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish() { return ingredientsOfTheDish; }

    public List<DishDetail> getDishDetail(String name) { return dataRepository.getDishDetail(name); }

    public Boolean ingredientExists(int idProduct, int idDish) {
        return dataRepository.ingredientExists(idProduct, idDish);
    }

    public void insert(IngredientsOfTheDish ingredientsOfTheDish, DataRepository.Executor executor) {
        dataRepository.insert(ingredientsOfTheDish, executor);
    }
}

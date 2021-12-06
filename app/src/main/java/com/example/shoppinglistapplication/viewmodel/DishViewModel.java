package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Dish;

import java.util.List;

public class DishViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Dish>> dishes;

    public DishViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        dishes = dataRepository.getAlphabetizedDishes();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return dishes;
    }

    public Integer getIdDishByName(String name) {
        return dataRepository.getIdByDishName(name); }

    public Boolean dishExists(String name) {
        return dataRepository.dishExists(name);
    }

    public void insert(Dish dish, DataRepository.Executor executor) {
        dataRepository.insert(dish, executor);
    }
}

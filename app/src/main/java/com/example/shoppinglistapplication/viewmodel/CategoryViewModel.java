package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Category>> categories;

    public CategoryViewModel(@NonNull Application application, String name, int id) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        categories = dataRepository.getAlphabetizedCategories();
    }

    public LiveData<List<Category>> getAllCategory() {
        return categories;
    }

    public Integer getIdCategoryByName(String name) { return dataRepository.getIdByCategoryName(name); }

    public Boolean categoryExists(String name) {
        return dataRepository.categoryExists(name);
    }

    public void insert(Category category, DataRepository.Executor executor) {
        dataRepository.insert(category, executor);
    }
}

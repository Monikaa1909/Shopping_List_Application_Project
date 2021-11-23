package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppinglistapplication.entities.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Category>> categories;

    private LiveData<List<Category>> categoriesById;
    private LiveData<Integer> categoriesById2;
    private LiveData<String> category_name_by_id;

    public CategoryViewModel(@NonNull Application application, String name, int id) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        categories = dataRepository.getAlphabetizedCategories();

        categoriesById = dataRepository.getIdByCategoryName(name);
        categoriesById2 = dataRepository.getIdByCategoryName2(name);
        category_name_by_id = dataRepository.getCategory_name_by_id(id);
//        categoryId3 = dataRepository.getId_category_by_name3(name);
//        int i = categoriesById2;
//        category_name = name;
//        categoriesById = dataRepository.getIdByCategoryName(name);
    }

    LiveData<List<Category>> getAllCategory() {
        return categories;
    }

    LiveData<List<Category>> getCategoriesById(String name) {
        return dataRepository.getIdByCategoryName(name);
    }

    LiveData<Integer> getCategoriesById2(String name) {
//        return categoriesById2;
        return dataRepository.getIdByCategoryName2(name);
    }

    LiveData<String> getCategory_name_by_id(int id) {
        return dataRepository.getCategory_name_by_id(id);
    }

    public void insert(Category category) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            dataRepository.insert(category);
        });
    }
}

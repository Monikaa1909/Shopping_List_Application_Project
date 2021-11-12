package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryDao categoryDao;
    private final LiveData<List<Category>> allCategory;

    public CategoryViewModel(Application application) {
        super(application);

        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        allCategory = categoryDao.getAlphabetizedCategories();
    }

    LiveData<List<Category>> getAllCategory() {
        return allCategory;
    }

//    LiveData<List<Integer>> getIdByCategoryName(String name) {
//         return categoryDao.getIdByCategoryName(name);
//    }

    public void insert(Category category) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.insert(category);
        });
    }
}

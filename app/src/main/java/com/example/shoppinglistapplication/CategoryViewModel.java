package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Category>> categories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        categories = dataRepository.getAlphabetizedCategories();
    }

    LiveData<List<Category>> getAllCategory() {
        return categories;
    }


    public void insert(Category category) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            dataRepository.insert(category);
        });
    }
}

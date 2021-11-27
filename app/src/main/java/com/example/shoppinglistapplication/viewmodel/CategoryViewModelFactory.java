package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;

public class CategoryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private Application application;

    private String categoryName;
    private int idCategory;

    public CategoryViewModelFactory(@NonNull Application application, String name, int id) {
        this.application = application;
        categoryName = name;
        idCategory = id;
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CategoryViewModel(application, categoryName, idCategory);
    }
}

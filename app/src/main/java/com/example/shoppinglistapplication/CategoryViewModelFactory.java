package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CategoryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private Application application;

    private String category_name;
    private int id_category;

    public CategoryViewModelFactory(@NonNull Application application, String name, int id) {
        this.application = application;
        category_name = name;
        id_category = id;
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CategoryViewModel(application, category_name, id_category);
    }
}

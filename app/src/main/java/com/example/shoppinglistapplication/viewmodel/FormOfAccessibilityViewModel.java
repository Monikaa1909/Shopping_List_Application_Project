package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;

import java.util.List;

public class FormOfAccessibilityViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<FormOfAccessibility>> forms;

    public FormOfAccessibilityViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        forms = dataRepository.getAllFormsOfAccessibility();
    }

    public LiveData<List<FormOfAccessibility>> getAllUnits() {
        return forms;
    }

//    public List<Product> getProductsByCategoryName(String name) { return dataRepository.getProductsByCategoryName(name); }

    public Boolean formExists(float form) {
        return dataRepository.formExists(form);
    }
    public long formIdByName(float form) {
        return dataRepository.formIdByName(form);
    }

    public void insert(FormOfAccessibility formOfAccessibility, DataRepository.Executor executor) {
        dataRepository.insert(formOfAccessibility, executor);
    }
}
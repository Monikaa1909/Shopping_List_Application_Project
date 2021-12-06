package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entity.ProductFormOfAccessibility;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;

import java.util.List;

public class ProductFormOfAccessibilityViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<ProductFormOfAccessibility>> productFormOfAccessibility;

    public ProductFormOfAccessibilityViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        productFormOfAccessibility = dataRepository.getAllProductFormOfAccessibility();
    }

    public LiveData<List<ProductFormOfAccessibility>> getAllProductFormOfAccessibility() { return productFormOfAccessibility; }


    public void insert(ProductFormOfAccessibility productFormOfAccessibility, DataRepository.Executor executor) {
        dataRepository.insert(productFormOfAccessibility, executor);
    }

    public void deleteProductFormOfAccessibilityById(long idProduct, long idForm, DataRepository.Executor executor) {
        dataRepository.deleteProductFormOfAccessibilityById(idProduct, idForm);
    }
}

package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.entities.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        products = dataRepository.getAlphabetizedProducts();
    }

    LiveData<List<Product>> getAllProducts() {
        return products;
    }

    public void insert(Product product) {
        dataRepository.insert(product);
    }
}

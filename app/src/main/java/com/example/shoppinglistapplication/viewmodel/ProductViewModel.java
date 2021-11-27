package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application, String name, int id) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        products = dataRepository.getAlphabetizedProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return products;
    }

    public List<Product> getProductsByCategoryName(String name) { return dataRepository.getProductsByCategoryName(name); }

    public Boolean productExists(String name) {
        return dataRepository.productExists(name);
    }

    public void insert(Product product, DataRepository.Executor executor) {
        dataRepository.insert(product, executor);
    }
}

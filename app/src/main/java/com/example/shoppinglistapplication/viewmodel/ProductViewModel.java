package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        products = dataRepository.getAlphabetizedProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return products;
    }

    public List<Product> getProductsByCategoryName(String name) { return dataRepository.getProductsByCategoryName(name); }

    public List<Product> getAlphabetizedProductsByCategory(int id) { return dataRepository.getAlphabetizedProductsByCategory(id); }

    public Boolean productExists(String name) {
        return dataRepository.productExists(name);
    }

    public List<UnitOfMeasurement> getProductUnit(int id) { return dataRepository.getProductUnit(id); }
    public List<FormOfAccessibility> getProductForm(int id) { return dataRepository.getProductForm(id); }
    public List<Category> getProductCategory(int id) { return dataRepository.getProductCategory(id); }

    public void insert(Product product, DataRepository.Executor executor) {
        dataRepository.insert(product, executor);
    }

    public void deleteProductById(long id, DataRepository.Executor executor) {
        dataRepository.deleteProductById(id);
    }

    public void updateProductName(long id, String newProductName, DataRepository.Executor executor) {
        dataRepository.updateProductName(id, newProductName);
    }

    public void updateProductCategory(long id, long newProductCategory, DataRepository.Executor executor) {
        dataRepository.updateProductCategory(id, newProductCategory);
    }

    public void updateProductUnit(long id, long newProductUnit, DataRepository.Executor executor) {
        dataRepository.updateProductUnit(id, newProductUnit);
    }
}

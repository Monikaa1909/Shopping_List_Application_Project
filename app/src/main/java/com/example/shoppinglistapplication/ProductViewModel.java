package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductDao productDao;
    private final LiveData<List<Product>> allProducts;

    public ProductViewModel(Application application) {
        super(application);

        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAlphabetizedProducts();
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public void insert(Product product) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }
}

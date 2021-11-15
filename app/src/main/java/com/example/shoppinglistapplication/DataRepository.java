package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {

    private static DataRepository INSTANCE;
    private final AppRoomDatabase database;

    private ProductDao productDao;
    private CategoryDao categoryDao;
    private LiveData<List<Product>>  products;
    private LiveData<List<Category>>  categories;

    private DataRepository(Application application) {
        database = AppRoomDatabase.getDatabase(application);

        productDao = database.productDao();
        categoryDao = database.categoryDao();
        products = productDao.getAlphabetizedProducts();
        categories = categoryDao.getAlphabetizedCategories();
    }

    public static DataRepository getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    LiveData<List<Product>> getAlphabetizedProducts() {
        return products;
    }

    LiveData<List<Category>> getAlphabetizedCategories() {
        return categories;
    }

//    LiveData<Category> getIdByCategoryName(String name) {
//        return database.categoryDao().getIdByCategoryName(name);
//    }

    void insert(Product product) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }

    void insert(Category category) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.insert(category);
        });
    }
}

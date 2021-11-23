package com.example.shoppinglistapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.daos.CategoryDao;
import com.example.shoppinglistapplication.daos.CompositionOfTheShoppingListDao;
import com.example.shoppinglistapplication.daos.DishDao;
import com.example.shoppinglistapplication.daos.FormOfAccessibilityDao;
import com.example.shoppinglistapplication.daos.IngredientsOfTheDishDao;
import com.example.shoppinglistapplication.daos.ListOfPreferencesDao;
import com.example.shoppinglistapplication.daos.ListOfPreferencesDishDao;
import com.example.shoppinglistapplication.daos.ProductDao;
import com.example.shoppinglistapplication.daos.ShoppingListDao;
import com.example.shoppinglistapplication.daos.UnitOfMeasurementDao;
import com.example.shoppinglistapplication.entities.Category;
import com.example.shoppinglistapplication.entities.FormOfAccessibility;
import com.example.shoppinglistapplication.entities.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entities.Product;
import com.example.shoppinglistapplication.entities.ShoppingList;

import java.util.List;

public class DataRepository {

    private static DataRepository INSTANCE;
    private final AppRoomDatabase database;

    private ProductDao productDao;
    private CategoryDao categoryDao;

    private LiveData<List<Product>>  products;
    private LiveData<List<Category>>  categories;
    private LiveData<Integer> id_category_by_name;
    private LiveData<String> category_name_by_id;

    private LiveData<List<Category>> categoriesByName;
    private String name_of_category;
    private int id_category;

    private DataRepository(Application application) {
        database = AppRoomDatabase.getDatabase(application);

        productDao = database.productDao();
        categoryDao = database.categoryDao();

        categoriesByName = categoryDao.getIdByCategoryName(name_of_category);
        id_category_by_name = categoryDao.getIdByCategoryName2(name_of_category);
        category_name_by_id = categoryDao.getCategoryNameById(id_category);

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
    LiveData<List<Category>> getAlphabetizedCategories() {
        return categories;
    }

    LiveData<List<Product>> getAlphabetizedProducts() {
        return products;
    }


    LiveData<List<Category>> getIdByCategoryName(String name) {
//        return categoriesByName;
        return categoryDao.getIdByCategoryName(name);
    }

    LiveData<Integer> getIdByCategoryName2(String name) {
//        return id_category_by_name;
        return categoryDao.getIdByCategoryName2(name);
    }

    LiveData<String> getCategory_name_by_id(int id) {
//        return category_name_by_id;
        return categoryDao.getCategoryNameById(id);
    }

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

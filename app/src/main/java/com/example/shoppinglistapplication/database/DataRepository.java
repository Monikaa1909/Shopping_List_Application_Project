package com.example.shoppinglistapplication.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.dao.CategoryDao;
import com.example.shoppinglistapplication.dao.DishDao;
import com.example.shoppinglistapplication.dao.IngredientsOfTheDishDao;
import com.example.shoppinglistapplication.dao.ListOfPreferencesDao;
import com.example.shoppinglistapplication.dao.ProductDao;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Dish;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;

import java.util.List;

public class DataRepository {

    private static DataRepository INSTANCE;
    private final AppRoomDatabase database;

    private ProductDao productDao;
    private CategoryDao categoryDao;
    private ListOfPreferencesDao listOfPreferencesDao;
    private DishDao dishDao;
    private IngredientsOfTheDishDao ingredientsOfTheDishDao;

    private LiveData<List<Product>>  products;
    private LiveData<List<Category>>  categories;
    private LiveData<List<ListOfPreferences>> listsOfPreferences;
    private LiveData<List<Dish>> dishes;
    private LiveData<List<IngredientsOfTheDish>> ingredientsOfTheDish;

    private DataRepository(Application application) {
        database = AppRoomDatabase.getDatabase(application);

        productDao = database.productDao();
        categoryDao = database.categoryDao();
        listOfPreferencesDao = database.listOfPreferencesDao();
        dishDao = database.dishDao();
        ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();

        products = productDao.getAlphabetizedProducts();
        categories = categoryDao.getAlphabetizedCategories();
        listsOfPreferences = listOfPreferencesDao.getAlphabetizedListOfPreferences();
        dishes = dishDao.getAlphabetizedDishes();
        ingredientsOfTheDish = ingredientsOfTheDishDao.getAllIngredientsOfTheDish();
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

    public LiveData<List<Category>> getAlphabetizedCategories() {
        return categories;
    }
    public LiveData<List<Product>> getAlphabetizedProducts() {
        return products;
    }
    public LiveData<List<ListOfPreferences>> getListsOfPreferences() {return listsOfPreferences; }
    public LiveData<List<Dish>> getAlphabetizedDishes() { return dishes; }
    public LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish() { return ingredientsOfTheDish; }

    public List<DishDetail> getDishDetail(String name) { return ingredientsOfTheDishDao.getDetail(name); }
    public List<Product> getProductsByCategoryName(String name) { return productDao.getProductsByCategoryName(name); }

    public Integer getIdByCategoryName(String name) { return categoryDao.getIdByCategoryName(name); }
    public Integer getIdByDishName(String name) { return dishDao.getIdByDishName(name); }

    public Boolean categoryExists(String name) {
        return categoryDao.categoryExists(name);
    }
    public Boolean productExists(String name) {
        return productDao.productExists(name);
    }
    public Boolean listOfPreferencesExists(String name) { return listOfPreferencesDao.listOfPreferencesExists(name); }
    public Boolean dishExists(String name) {return dishDao.dishExists(name); }

    public void insert(Product product, Executor executor) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = productDao.insert(product);
            executor.execute(id);
        });
    }

    public void insert(ListOfPreferences list) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            listOfPreferencesDao.insert(list);
        });
    }

    public void insert(Category category, Executor executor) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = categoryDao.insert(category);
            executor.execute(id);
        });
    }

    public void insert(Dish dish, Executor executor) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = dishDao.insert(dish);
            executor.execute(id);
        });
    }

    public void insert(IngredientsOfTheDish ingredientsOfTheDish, Executor executor) {
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = ingredientsOfTheDishDao.insert(ingredientsOfTheDish);
            executor.execute(id);
        });
    }

    public interface Executor {
        void execute(long id);
    }
}

package com.example.shoppinglistapplication.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.dao.CategoryDao;
import com.example.shoppinglistapplication.dao.DishDao;
import com.example.shoppinglistapplication.dao.FormOfAccessibilityDao;
import com.example.shoppinglistapplication.dao.IngredientsOfTheDishDao;
import com.example.shoppinglistapplication.dao.ListOfPreferencesDao;
import com.example.shoppinglistapplication.dao.ListOfPreferencesDishDao;
import com.example.shoppinglistapplication.dao.ProductDao;
import com.example.shoppinglistapplication.dao.ProductFormOfAccessibilityDao;
import com.example.shoppinglistapplication.dao.ShoppingListDao;
import com.example.shoppinglistapplication.dao.SimpleCompositionOfTheShoppingListDao;
import com.example.shoppinglistapplication.dao.UnitOfMeasurementDao;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.Dish;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.ProductFormOfAccessibility;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

public class DataRepository {

    private static DataRepository INSTANCE;
    private final AppRoomDatabase database;

    private ProductDao productDao = null;
    private CategoryDao categoryDao = null;
    private ListOfPreferencesDao listOfPreferencesDao = null;
    private DishDao dishDao = null;
    private IngredientsOfTheDishDao ingredientsOfTheDishDao = null;
    private UnitOfMeasurementDao unitOfMeasurementDao = null;
    private FormOfAccessibilityDao formOfAccessibilityDao = null;
    private ProductFormOfAccessibilityDao productFormOfAccessibilityDao = null;
    private ListOfPreferencesDishDao listOfPreferencesDishDao = null;
    private ShoppingListDao shoppingListDao = null;
    private SimpleCompositionOfTheShoppingListDao simpleCompositionOfTheShoppingListDao = null;

    private LiveData<List<Product>>  products = null;
    private LiveData<List<Category>>  categories = null;
    private LiveData<List<ListOfPreferences>> listsOfPreferences = null;
    private LiveData<List<Dish>> dishes = null;
    private LiveData<List<IngredientsOfTheDish>> ingredientsOfTheDish = null;
    private LiveData<List<UnitOfMeasurement>>  units = null;
    private LiveData<List<FormOfAccessibility>>  formOfAccessibility = null;
    private LiveData<List<ProductFormOfAccessibility>> productFormOfAccessibility = null;
    private LiveData<List<ListOfPreferencesDish>> listOfPreferencesDish = null;
    private LiveData<List<ShoppingList>> shoppingList = null;
    private LiveData<List<SimpleCompositionOfTheShoppingList>> compositionOfTheShoppingList = null;

    private DataRepository(Application application) {
        database = AppRoomDatabase.getDatabase(application);

//        productDao = database.productDao();
//        categoryDao = database.categoryDao();
//        listOfPreferencesDao = database.listOfPreferencesDao();
//        dishDao = database.dishDao();
//        ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
//        unitOfMeasurementDao = database.unitOfMeasurementDao();
//        formOfAccessibilityDao = database.formOfAccessibilityDao();
//        productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
//        listOfPreferencesDishDao = database.listOfPreferencesDishDao();
//        shoppingListDao = database.shoppingListDao();
//        compositionOfTheShoppingListDao = database.compositionOfTheShoppingListDao();
//
//        products = productDao.getAlphabetizedProducts();
//        categories = categoryDao.getAlphabetizedCategories();
//        listsOfPreferences = listOfPreferencesDao.getAlphabetizedListOfPreferences();
//        dishes = dishDao.getAlphabetizedDishes();
//        ingredientsOfTheDish = ingredientsOfTheDishDao.getAllIngredientsOfTheDish();
//        units = unitOfMeasurementDao.getAllUnits();
//        formOfAccessibility = formOfAccessibilityDao.getAllForms();
//        productFormOfAccessibility = productFormOfAccessibilityDao.getAllProductFormOfAccessibility();
//        listOfPreferencesDish = listOfPreferencesDishDao.getAllListOfPreferencesDish();
//        shoppingList = shoppingListDao.getAlphabetizedShoppingLists();
//        compositionOfTheShoppingList = compositionOfTheShoppingListDao.getAllCompositions();
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
        if (categoryDao == null) {
            categoryDao = database.categoryDao();
            categories = categoryDao.getAlphabetizedCategories();
        }
        return categories;
    }

    public LiveData<List<Product>> getAlphabetizedProducts() {
        if (productDao == null) {
            productDao = database.productDao();
            products = productDao.getAlphabetizedProducts();
        }
        return products;
    }

    public LiveData<List<ListOfPreferences>> getListsOfPreferences() {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
            listsOfPreferences = listOfPreferencesDao.getAlphabetizedListOfPreferences();
        }
        return listsOfPreferences;
    }

    public LiveData<List<Dish>> getAlphabetizedDishes() {
        if (dishDao == null) {
            dishDao = database.dishDao();
            dishes = dishDao.getAlphabetizedDishes();
        }
        return dishes;
    }

    public LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish() {
        if (ingredientsOfTheDishDao == null) {
            ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
            ingredientsOfTheDish = ingredientsOfTheDishDao.getAllIngredientsOfTheDish();
        }
        return ingredientsOfTheDish;
    }

    public LiveData<List<UnitOfMeasurement>> getAllUnits() {
        if (unitOfMeasurementDao == null) {
            unitOfMeasurementDao = database.unitOfMeasurementDao();
            units = unitOfMeasurementDao.getAllUnits();
        }
        return units;
    }

    public LiveData<List<FormOfAccessibility>> getAllFormsOfAccessibility() {
        if (formOfAccessibilityDao == null) {
            formOfAccessibilityDao = database.formOfAccessibilityDao();
            formOfAccessibility = formOfAccessibilityDao.getAllForms();
        }
        return formOfAccessibility;
    }

    public LiveData<List<ProductFormOfAccessibility>> getAllProductFormOfAccessibility() {
        if (productFormOfAccessibilityDao == null) {
            productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
            productFormOfAccessibility = productFormOfAccessibilityDao.getAllProductFormOfAccessibility();
        }
        return productFormOfAccessibility;
    }

    public LiveData<List<ListOfPreferencesDish>> getAllListOfPreferencesDish() {
        if (listOfPreferencesDishDao == null) {
            listOfPreferencesDishDao = database.listOfPreferencesDishDao();
            listOfPreferencesDish = listOfPreferencesDishDao.getAllListOfPreferencesDish();
        }
        return listOfPreferencesDish;
    }

    public LiveData<List<ShoppingList>> getAllShoppingList() {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
            shoppingList = shoppingListDao.getAlphabetizedShoppingLists();
        }
        return shoppingList;
    }

    public LiveData<List<SimpleCompositionOfTheShoppingList>> getCompositionOfTheShoppingList() {
        if (simpleCompositionOfTheShoppingListDao == null) {
            simpleCompositionOfTheShoppingListDao = database.compositionOfTheShoppingListDao();
            compositionOfTheShoppingList = simpleCompositionOfTheShoppingListDao.getAllCompositions();
        }
        return compositionOfTheShoppingList;
    }

    public List<ShoppingListDetail> getShoppingListDetail(int idListOfPreferences) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        return listOfPreferencesDao.getShoppingListDetail(idListOfPreferences);
    }

    public List<ShoppingListDetail> getShoppingListDetail2(int idListOfPreferences) {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
        }
        return shoppingListDao.getShoppingListDetail(idListOfPreferences);
    }

    public List<ShoppingListDetail> getShoppingListDetailByShoppingListId(int idShoppingList) {
        if (simpleCompositionOfTheShoppingListDao == null) {
            simpleCompositionOfTheShoppingListDao = database.compositionOfTheShoppingListDao();
        }
        return simpleCompositionOfTheShoppingListDao.getShoppingListDetailByShoppingListId(idShoppingList);
    }

    public List<ShoppingListDetail> getShoppingListDetailByPreferencesListId(int idListOfPreferences) {
        if (simpleCompositionOfTheShoppingListDao == null) {
            simpleCompositionOfTheShoppingListDao = database.compositionOfTheShoppingListDao();
        }
        return simpleCompositionOfTheShoppingListDao.getShoppingListDetailByPreferencesListId(idListOfPreferences);
    }

    public List<DishDetail> getDishDetail(int idDish) {
        if (ingredientsOfTheDishDao == null) {
            ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
        }
        return ingredientsOfTheDishDao.getDetail(idDish);
    }

    public List<ListOfPreferencesDetail> getListOfPreferencesDishDetail(int idListOfPreferences) {
        if (listOfPreferencesDishDao == null) {
            listOfPreferencesDishDao = database.listOfPreferencesDishDao();
        }
        return listOfPreferencesDishDao.getListOfPreferencesDishDetail(idListOfPreferences);
    }

    public List<Product> getProductsByCategoryName(String name) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        return productDao.getProductsByCategoryName(name);
    }

    public List<Product> getAlphabetizedProductsByCategory(int id) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        return productDao.getAlphabetizedProductsByCategory(id);
    }

    public long getIdByCategoryName(String name) {
        if (categoryDao == null) {
            categoryDao = database.categoryDao();
        }
        return categoryDao.getIdByCategoryName(name);
    }

    public Integer getIdByDishName(String name) {
        if (dishDao == null) {
            dishDao = database.dishDao();
        }
        return dishDao.getIdByDishName(name);
    }

    public Integer getIdByListOfPreferencesName(String name) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        return listOfPreferencesDao.getIdByListOfPreferencesName(name);
    }

    public Integer getIdByShoppingListName(String name) {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
        }
        return shoppingListDao.getIdByShoppingListName(name);
    }

    public String getNameByListOfPreferencesId(int id) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        return listOfPreferencesDao.getNameByListOfPreferencesId(id);
    }

    public Boolean categoryExists(String name) {
        if (categoryDao == null) {
            categoryDao = database.categoryDao();
        }
        return categoryDao.categoryExists(name);
    }

    public Boolean productExists(String name) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        return productDao.productExists(name);
    }

    public Boolean listOfPreferencesExists(String name) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        return listOfPreferencesDao.listOfPreferencesExists(name);
    }

    public Boolean dishExists(String name) {
        if (dishDao == null) {
            dishDao = database.dishDao();
        }
        return dishDao.dishExists(name);
    }

    public Boolean unitExists(String name) {
        if (unitOfMeasurementDao == null) {
            unitOfMeasurementDao = database.unitOfMeasurementDao();
        }
        return unitOfMeasurementDao.unitExists(name);
    }

    public Boolean formExists(float form) {
        if (formOfAccessibilityDao == null) {
            formOfAccessibilityDao = database.formOfAccessibilityDao();
        }
        return formOfAccessibilityDao.formExists(form);
    }

    public Boolean ingredientExists(int idProduct, int idDish) {
        if (ingredientsOfTheDishDao == null) {
            ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
        }
        return ingredientsOfTheDishDao.ingredientsExists(idProduct, idDish);
    }

    public Boolean listOfPreferencesDishExists(int idDish, int idListOfPreferences) {
        if (listOfPreferencesDishDao == null) {
            listOfPreferencesDishDao = database.listOfPreferencesDishDao();
        }
        return listOfPreferencesDishDao.listOfPreferencesDishExists(idDish, idListOfPreferences);
    }

    public Boolean shoppingListExists(String name) {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
        }
        return shoppingListDao.shoppingListExists(name);
    }

    public long formIdByName(float form) {
        if (formOfAccessibilityDao == null) {
            formOfAccessibilityDao = database.formOfAccessibilityDao();
        }
        return formOfAccessibilityDao.formIdByName(form);
    }

    public long unitIdByName(String name) {
        if (unitOfMeasurementDao == null) {
            unitOfMeasurementDao = database.unitOfMeasurementDao();
        }
        return unitOfMeasurementDao.unitIdByName(name);
    }

    public List<UnitOfMeasurement> getProductUnit(int id) {
        if (productFormOfAccessibilityDao == null) {
            productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
        }
        return productDao.getProductUnit(id);
    }

    public List<FormOfAccessibility> getProductForm(int id) {
        if (productFormOfAccessibilityDao == null) {
            productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
        }
        return productDao.getProductForm(id);
    }

    public List<Category> getProductCategory(int id) {
        if (productFormOfAccessibilityDao == null) {
            productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
        }
        return productDao.getProductCategory(id);
    }

    public void insert(Product product, Executor executor) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = productDao.insert(product);
            executor.execute(id);
        });
    }

    public void insert(ListOfPreferences list) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            listOfPreferencesDao.insert(list);
        });
    }

    public void insert(Category category, Executor executor) {
        if (categoryDao == null) {
            categoryDao = database.categoryDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = categoryDao.insert(category);
            executor.execute(id);
        });
    }

    public void insert(Dish dish, Executor executor) {
        if (dishDao == null) {
            dishDao = database.dishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = dishDao.insert(dish);
            executor.execute(id);
        });
    }

    public void insert(IngredientsOfTheDish ingredientsOfTheDish, Executor executor) {
        if (ingredientsOfTheDishDao == null) {
            ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = ingredientsOfTheDishDao.insert(ingredientsOfTheDish);
            executor.execute(id);
        });
    }

    public void insert(UnitOfMeasurement unit, Executor executor) {
        if (unitOfMeasurementDao == null) {
            unitOfMeasurementDao = database.unitOfMeasurementDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = unitOfMeasurementDao.insert(unit);
            executor.execute(id);
        });
    }

    public void insert(FormOfAccessibility form, Executor executor) {
        if (formOfAccessibilityDao == null) {
            formOfAccessibilityDao = database.formOfAccessibilityDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = formOfAccessibilityDao.insert(form);
            executor.execute(id);
        });
    }

    public void insert(ProductFormOfAccessibility productFormOfAccessibility, Executor executor) {
        if (productFormOfAccessibilityDao == null) {
            productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = productFormOfAccessibilityDao.insert(productFormOfAccessibility);
            executor.execute(id);
        });
    }

    public void insert(ListOfPreferencesDish listOfPreferencesDish, Executor executor) {
        if (listOfPreferencesDishDao == null) {
            listOfPreferencesDishDao = database.listOfPreferencesDishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = listOfPreferencesDishDao.insert(listOfPreferencesDish);
            executor.execute(id);
        });
    }

    public void insert(ShoppingList shoppingList, Executor executor) {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = shoppingListDao.insert(shoppingList);
            executor.execute(id);
        });
    }

    public void insert(SimpleCompositionOfTheShoppingList simpleCompositionOfTheShoppingList, Executor executor) {
        if (simpleCompositionOfTheShoppingListDao == null) {
            simpleCompositionOfTheShoppingListDao = database.compositionOfTheShoppingListDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            long id = simpleCompositionOfTheShoppingListDao.insert(simpleCompositionOfTheShoppingList);
            executor.execute(id);
        });
    }

    public void deleteProductById(long id) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.deleteProductById(id);
        });
    }

    public void deleteCategoryById(long id) {
        if (categoryDao == null) {
            categoryDao = database.categoryDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.deleteCategoryById(id);
        });
    }

    public void deleteFormById(long id) {
        if (formOfAccessibilityDao == null) {
            formOfAccessibilityDao = database.formOfAccessibilityDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            formOfAccessibilityDao.deleteFormById(id);
        });
    }

    public void deleteUnitById(long id) {
        if (unitOfMeasurementDao == null) {
            unitOfMeasurementDao = database.unitOfMeasurementDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            unitOfMeasurementDao.deleteUnitById(id);
        });
    }

    public void deleteProductFormOfAccessibilityById(long idProduct, long idForm) {
        if (productFormOfAccessibilityDao == null) {
            productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productFormOfAccessibilityDao.deleteProductFormOfAccessibilityById(idProduct, idForm);
        });
    }

    public void deleteDishById(long id) {
        if (dishDao == null) {
            dishDao = database.dishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            dishDao.deleteDishById(id);
        });
    }

    public void deleteIngredientOfTheDish(int idProduct, int idDish) {
        if (ingredientsOfTheDishDao == null) {
            ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            ingredientsOfTheDishDao.deleteIngredientOfTheDish(idProduct, idDish);
        });
    }

    public void deleteListOfPreferencesDish(int idDish, int idListOfPreferences) {
        if (listOfPreferencesDishDao == null) {
            listOfPreferencesDishDao = database.listOfPreferencesDishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            listOfPreferencesDishDao.deleteListOfPreferencesDish(idDish, idListOfPreferences);
        });
    }

    public void deleteListOfPreferences(int idListOfPreferences) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            listOfPreferencesDao.deleteListOfPreferencesById(idListOfPreferences);
        });
    }

    public void deleteShoppingList(int idListOfPreferences) {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            shoppingListDao.deleteShoppingListById(idListOfPreferences);
        });
    }

    public void updateProductName(long id, String newProductName) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.updateProductName(id, newProductName);
        });
    }

    public void updateProductCategory(long id, long newProductCategory) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.updateProductCategory(id, newProductCategory);
        });
    }

    public void updateProductUnit(long id, long newProductUnit) {
        if (productDao == null) {
            productDao = database.productDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.updateProductUnit(id, newProductUnit);
        });
    }

    public void updateCategoryName(long id, String newCategoryName) {
        if (categoryDao == null) {
            categoryDao = database.categoryDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.updateCategoryName(id, newCategoryName);
        });
    }

    public void updateDishName(long id, String newDishName) {
        if (dishDao == null) {
            dishDao = database.dishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            dishDao.updateDishName(id, newDishName);
        });
    }

    public void updateIngredientOfTheDish(int idProduct, int idDish, float quantity) {
        if (ingredientsOfTheDishDao == null) {
            ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            ingredientsOfTheDishDao.updateIngredientQuantity(idProduct, idDish, quantity);
        });
    }

    public void updateListOfPreferencesDishPortions(int idDish, int idListOfPreferences, int portions) {
        if (listOfPreferencesDishDao == null) {
            listOfPreferencesDishDao = database.listOfPreferencesDishDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            listOfPreferencesDishDao.updateListOfPreferencesDishPortions(idDish, idListOfPreferences, portions);
        });
    }

    public void updateListOfPreferences(int idListOfPreferences, String name) {
        if (listOfPreferencesDao == null) {
            listOfPreferencesDao = database.listOfPreferencesDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            listOfPreferencesDao.updateListOfPreferencesName(idListOfPreferences, name);
        });
    }

    public void updateShoppingList(int idShoppingList, String name) {
        if (shoppingListDao == null) {
            shoppingListDao = database.shoppingListDao();
        }
        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
            shoppingListDao.updateShoppingListName(idShoppingList, name);
        });
    }

    public interface Executor {
        void execute(long id);
    }


//    private static DataRepository INSTANCE;
//    private final AppRoomDatabase database;
//
//    private ProductDao productDao;
//    private CategoryDao categoryDao;
//    private ListOfPreferencesDao listOfPreferencesDao;
//    private DishDao dishDao;
//    private IngredientsOfTheDishDao ingredientsOfTheDishDao;
//    private UnitOfMeasurementDao unitOfMeasurementDao;
//    private FormOfAccessibilityDao formOfAccessibilityDao;
//    private ProductFormOfAccessibilityDao productFormOfAccessibilityDao;
//    private ListOfPreferencesDishDao listOfPreferencesDishDao;
//    private ShoppingListDao shoppingListDao;
//    private CompositionOfTheShoppingListDao compositionOfTheShoppingListDao;
//
//    private LiveData<List<Product>>  products;
//    private LiveData<List<Category>>  categories;
//    private LiveData<List<ListOfPreferences>> listsOfPreferences;
//    private LiveData<List<Dish>> dishes;
//    private LiveData<List<IngredientsOfTheDish>> ingredientsOfTheDish;
//    private LiveData<List<UnitOfMeasurement>>  units;
//    private LiveData<List<FormOfAccessibility>>  formOfAccessibility;
//    private LiveData<List<ProductFormOfAccessibility>> productFormOfAccessibility;
//    private LiveData<List<ListOfPreferencesDish>> listOfPreferencesDish;
//    private LiveData<List<ShoppingList>> shoppingList;
//    private LiveData<List<CompositionOfTheShoppingList>> compositionOfTheShoppingList;
//
//    private DataRepository(Application application) {
//        database = AppRoomDatabase.getDatabase(application);
//
//        productDao = database.productDao();
//        categoryDao = database.categoryDao();
//        listOfPreferencesDao = database.listOfPreferencesDao();
//        dishDao = database.dishDao();
//        ingredientsOfTheDishDao = database.ingredientsOfTheDishDao();
//        unitOfMeasurementDao = database.unitOfMeasurementDao();
//        formOfAccessibilityDao = database.formOfAccessibilityDao();
//        productFormOfAccessibilityDao = database.productFormOfAccessibilityDao();
//        listOfPreferencesDishDao = database.listOfPreferencesDishDao();
//        shoppingListDao = database.shoppingListDao();
//        compositionOfTheShoppingListDao = database.compositionOfTheShoppingListDao();
//
//        products = productDao.getAlphabetizedProducts();
//        categories = categoryDao.getAlphabetizedCategories();
//        listsOfPreferences = listOfPreferencesDao.getAlphabetizedListOfPreferences();
//        dishes = dishDao.getAlphabetizedDishes();
//        ingredientsOfTheDish = ingredientsOfTheDishDao.getAllIngredientsOfTheDish();
//        units = unitOfMeasurementDao.getAllUnits();
//        formOfAccessibility = formOfAccessibilityDao.getAllForms();
//        productFormOfAccessibility = productFormOfAccessibilityDao.getAllProductFormOfAccessibility();
//        listOfPreferencesDish = listOfPreferencesDishDao.getAllListOfPreferencesDish();
//        shoppingList = shoppingListDao.getAlphabetizedShoppingLists();
//        compositionOfTheShoppingList = compositionOfTheShoppingListDao.getAllCompositions();
//    }
//
//    public static DataRepository getInstance(Application application) {
//        if (INSTANCE == null) {
//            synchronized (DataRepository.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new DataRepository(application);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public LiveData<List<Category>> getAlphabetizedCategories() {
//        return categories;
//    }
//    public LiveData<List<Product>> getAlphabetizedProducts() {
//        return products;
//    }
//    public LiveData<List<ListOfPreferences>> getListsOfPreferences() {return listsOfPreferences; }
//    public LiveData<List<Dish>> getAlphabetizedDishes() { return dishes; }
//    public LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish() { return ingredientsOfTheDish; }
//    public LiveData<List<UnitOfMeasurement>> getAllUnits() { return units; }
//    public LiveData<List<FormOfAccessibility>> getAllFormsOfAccessibility() { return formOfAccessibility; }
//    public LiveData<List<ProductFormOfAccessibility>> getAllProductFormOfAccessibility() { return productFormOfAccessibility; }
//    public LiveData<List<ListOfPreferencesDish>> getAllListOfPreferencesDish() { return listOfPreferencesDish; }
//    public LiveData<List<ShoppingList>> getAllShoppingList() { return shoppingList; }
//    public LiveData<List<CompositionOfTheShoppingList>> getCompositionOfTheShoppingList() { return compositionOfTheShoppingList; }
//
//    public List<ShoppingListDetail> getShoppingListDetail(int idListOfPreferences) {return listOfPreferencesDao.getShoppingListDetail(idListOfPreferences); }
//    public List<ShoppingListDetail> getShoppingListDetail2(int idListOfPreferences) {return shoppingListDao.getShoppingListDetail(idListOfPreferences); }
//    public List<ShoppingListDetail> getShoppingListDetailByShoppingListId(int idShoppingList) {return compositionOfTheShoppingListDao.getShoppingListDetailByShoppingListId(idShoppingList);}
//    public List<ShoppingListDetail> getShoppingListDetailByPreferencesListId(int idListOfPreferences) {return compositionOfTheShoppingListDao.getShoppingListDetailByPreferencesListId(idListOfPreferences); }
//    public List<DishDetail> getDishDetail(int idDish) { return ingredientsOfTheDishDao.getDetail(idDish); }
//    public List<ListOfPreferencesDetail> getListOfPreferencesDishDetail(int idListOfPreferences) { return listOfPreferencesDishDao.getListOfPreferencesDishDetail(idListOfPreferences); }
//    public List<Product> getProductsByCategoryName(String name) { return productDao.getProductsByCategoryName(name); }
//    public List<Product> getAlphabetizedProductsByCategory(int id) { return productDao.getAlphabetizedProductsByCategory(id); }
//
//    public long getIdByCategoryName(String name) { return categoryDao.getIdByCategoryName(name); }
//    public Integer getIdByDishName(String name) { return dishDao.getIdByDishName(name); }
//    public Integer getIdByListOfPreferencesName(String name) { return listOfPreferencesDao.getIdByListOfPreferencesName(name); }
//    public Integer getIdByShoppingListName(String name) { return shoppingListDao.getIdByShoppingListName(name); }
//    public String getNameByListOfPreferencesId(int id) {return listOfPreferencesDao.getNameByListOfPreferencesId(id); }
//
//    public Boolean categoryExists(String name) {
//        return categoryDao.categoryExists(name);
//    }
//    public Boolean productExists(String name) {
//        return productDao.productExists(name);
//    }
//    public Boolean listOfPreferencesExists(String name) { return listOfPreferencesDao.listOfPreferencesExists(name); }
//    public Boolean dishExists(String name) {return dishDao.dishExists(name); }
//    public Boolean unitExists(String name) {
//        return unitOfMeasurementDao.unitExists(name);
//    }
//    public Boolean formExists(float form) { return formOfAccessibilityDao.formExists(form); }
//    public Boolean ingredientExists(int idProduct, int idDish) { return ingredientsOfTheDishDao.ingredientsExists(idProduct, idDish); }
//    public Boolean listOfPreferencesDishExists(int idDish, int idListOfPreferences) { return listOfPreferencesDishDao.listOfPreferencesDishExists(idDish, idListOfPreferences); }
//    public Boolean shoppingListExists(String name) { return shoppingListDao.shoppingListExists(name); }
//
//    public long formIdByName(float form) { return formOfAccessibilityDao.formIdByName(form);}
//    public long unitIdByName(String name) { return unitOfMeasurementDao.unitIdByName(name); }
//
//    public List<UnitOfMeasurement> getProductUnit(int id) { return productDao.getProductUnit(id); }
//    public List<FormOfAccessibility> getProductForm(int id) { return productDao.getProductForm(id); }
//    public List<Category> getProductCategory(int id) { return productDao.getProductCategory(id); }
//
//    public void insert(Product product, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = productDao.insert(product);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(ListOfPreferences list) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            listOfPreferencesDao.insert(list);
//        });
//    }
//
//    public void insert(Category category, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = categoryDao.insert(category);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(Dish dish, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = dishDao.insert(dish);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(IngredientsOfTheDish ingredientsOfTheDish, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = ingredientsOfTheDishDao.insert(ingredientsOfTheDish);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(UnitOfMeasurement unit, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = unitOfMeasurementDao.insert(unit);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(FormOfAccessibility form, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = formOfAccessibilityDao.insert(form);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(ProductFormOfAccessibility productFormOfAccessibility, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = productFormOfAccessibilityDao.insert(productFormOfAccessibility);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(ListOfPreferencesDish listOfPreferencesDish, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = listOfPreferencesDishDao.insert(listOfPreferencesDish);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(ShoppingList shoppingList, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = shoppingListDao.insert(shoppingList);
//            executor.execute(id);
//        });
//    }
//
//    public void insert(CompositionOfTheShoppingList compositionOfTheShoppingList, Executor executor) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            long id = compositionOfTheShoppingListDao.insert(compositionOfTheShoppingList);
//            executor.execute(id);
//        });
//    }
//
//    public void deleteProductById(long id) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            productDao.deleteProductById(id);
//        });
//    }
//
//    public void deleteCategoryById(long id) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            categoryDao.deleteCategoryById(id);
//        });
//    }
//
//    public void deleteFormById(long id) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            formOfAccessibilityDao.deleteFormById(id);
//        });
//    }
//
//    public void deleteUnitById(long id) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            unitOfMeasurementDao.deleteUnitById(id);
//        });
//    }
//
//    public void deleteProductFormOfAccessibilityById(long idProduct, long idForm) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            productFormOfAccessibilityDao.deleteProductFormOfAccessibilityById(idProduct, idForm);
//        });
//    }
//
//    public void deleteDishById(long id) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            dishDao.deleteDishById(id);
//        });
//    }
//
//    public void deleteIngredientOfTheDish(int idProduct, int idDish) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            ingredientsOfTheDishDao.deleteIngredientOfTheDish(idProduct, idDish);
//        });
//    }
//
//    public void deleteListOfPreferencesDish(int idDish, int idListOfPreferences) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            listOfPreferencesDishDao.deleteListOfPreferencesDish(idDish, idListOfPreferences);
//        });
//    }
//
//    public void deleteListOfPreferences(int idListOfPreferences) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            listOfPreferencesDao.deleteListOfPreferencesById(idListOfPreferences);
//        });
//    }
//
//    public void deleteShoppingList(int idListOfPreferences) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            shoppingListDao.deleteShoppingListById(idListOfPreferences);
//        });
//    }
//
//    public void updateProductName(long id, String newProductName) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            productDao.updateProductName(id, newProductName);
//        });
//    }
//
//    public void updateProductCategory(long id, long newProductCategory) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            productDao.updateProductCategory(id, newProductCategory);
//        });
//    }
//
//    public void updateProductUnit(long id, long newProductUnit) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            productDao.updateProductUnit(id, newProductUnit);
//        });
//    }
//
//    public void updateCategoryName(long id, String newCategoryName) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            categoryDao.updateCategoryName(id, newCategoryName);
//        });
//    }
//
//    public void updateDishName(long id, String newDishName) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            dishDao.updateDishName(id, newDishName);
//        });
//    }
//
//    public void updateIngredientOfTheDish(int idProduct, int idDish, float quantity) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            ingredientsOfTheDishDao.updateIngredientQuantity(idProduct, idDish, quantity);
//        });
//    }
//
//    public void updateListOfPreferencesDishPortions(int idDish, int idListOfPreferences, int portions) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            listOfPreferencesDishDao.updateListOfPreferencesDishPortions(idDish, idListOfPreferences, portions);
//        });
//    }
//
//    public void updateListOfPreferences(int idListOfPreferences, String name) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            listOfPreferencesDao.updateListOfPreferencesName(idListOfPreferences, name);
//        });
//    }
//
//    public void updateShoppingList(int idShoppingList, String name) {
//        AppRoomDatabase.databaseWriteExecutor.execute(() -> {
//            shoppingListDao.updateShoppingListName(idShoppingList, name);
//        });
//    }
//
//    public interface Executor {
//        void execute(long id);
//    }
}

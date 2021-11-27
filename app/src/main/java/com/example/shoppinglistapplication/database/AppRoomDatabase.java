package com.example.shoppinglistapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.shoppinglistapplication.dao.CategoryDao;
import com.example.shoppinglistapplication.dao.CompositionOfTheShoppingListDao;
import com.example.shoppinglistapplication.dao.DishDao;
import com.example.shoppinglistapplication.dao.FormOfAccessibilityDao;
import com.example.shoppinglistapplication.dao.IngredientsOfTheDishDao;
import com.example.shoppinglistapplication.dao.ListOfPreferencesDao;
import com.example.shoppinglistapplication.dao.ListOfPreferencesDishDao;
import com.example.shoppinglistapplication.dao.ProductDao;
import com.example.shoppinglistapplication.dao.ShoppingListDao;
import com.example.shoppinglistapplication.dao.UnitOfMeasurementDao;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.CompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.Dish;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {  Product.class,
                        Category.class,
                        UnitOfMeasurement.class,
                        FormOfAccessibility.class,
                        Dish.class,
                        IngredientsOfTheDish.class,
                        ListOfPreferences.class,
                        ListOfPreferencesDish.class,
                        ShoppingList.class,
                        CompositionOfTheShoppingList.class},
                        version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
    public abstract FormOfAccessibilityDao formOfAccessibilityDao();
    public abstract UnitOfMeasurementDao unitOfMeasurementDao();
    public abstract CompositionOfTheShoppingListDao compositionOfTheShoppingListDao();
    public abstract DishDao dishDao();
    public abstract IngredientsOfTheDishDao ingredientsOfTheDishDao();
    public abstract ListOfPreferencesDao listOfPreferencesDao();
    public abstract ListOfPreferencesDishDao listOfPreferencesDishDao();
    public abstract ShoppingListDao shoppingListDao();

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database_wersja004")
                            .addCallback(roomDataBaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDataBaseCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                CategoryDao categoryDao = INSTANCE.categoryDao();
                categoryDao.deleteAll();
                Category category1 = new Category("Inne");      // id:1
                categoryDao.insert(category1);
                Category category2 = new Category("Warzywa");   // id:2
                categoryDao.insert(category2);
                Category category3 = new Category("Owoce");     // id:3
                categoryDao.insert(category3);
                Category category4 = new Category("Mięso");     // id:4
                categoryDao.insert(category4);
                Category category5 = new Category("Nabiał");    // id:5
                categoryDao.insert(category5);
                Category category6 = new Category("Pieczywo");  // id:6
                categoryDao.insert(category6);

                FormOfAccessibilityDao formOfAccessibilityDao = INSTANCE.formOfAccessibilityDao();
                formOfAccessibilityDao.deleteAll();
                FormOfAccessibility formOfAccessibility = new FormOfAccessibility((float) 0);    // id:1
                formOfAccessibilityDao.insert(formOfAccessibility);

                UnitOfMeasurementDao unitOfMeasurementDao = INSTANCE.unitOfMeasurementDao();
                unitOfMeasurementDao.deleteAll();
                UnitOfMeasurement unitOfMeasurement1 = new UnitOfMeasurement("Gram");   // id:1
                unitOfMeasurementDao.insert(unitOfMeasurement1);
                UnitOfMeasurement unitOfMeasurement2 = new UnitOfMeasurement("Litr");   // id:2
                unitOfMeasurementDao.insert(unitOfMeasurement2);

                ProductDao productDao = INSTANCE.productDao();
                productDao.deleteAll();
                Product product1 = new Product("Jabłko", 3, 1, 1);  // id:1
                productDao.insert(product1);
                Product product2 = new Product("Gruszka", 3, 1, 1); // id:2
                productDao.insert(product2);
                Product product3 = new Product("Pomarańcza", 3, 1, 1);  // id:3
                productDao.insert(product3);
                Product product4 = new Product("Banan", 3, 1, 1);   // id:4
                productDao.insert(product4);
                Product product5 = new Product("Malina", 3, 1, 1);  // id:5
                productDao.insert(product5);
                Product product6 = new Product("Marchewka", 2, 1, 1);   // id:6
                productDao.insert(product6);
                Product product7 = new Product("Ziemniak", 2, 1, 1);    // id:7
                productDao.insert(product7);
                Product product8 = new Product("Pomidor", 2, 1, 1); // id:8
                productDao.insert(product8);
                Product product9 = new Product("Kurczak", 4, 1, 1); // id:9
                productDao.insert(product9);
                Product product10 = new Product("Wołowina", 4, 1, 1);   // id:10
                productDao.insert(product10);
                Product product11 = new Product("Chleb", 6, 1, 1);  // id:11
                productDao.insert(product11);
                Product product12 = new Product("Chleb", 6, 1, 1);  // id:12
                productDao.insert(product12);
                Product product13 = new Product("Mleko", 2, 1, 2);  // id:13
                productDao.insert(product13);
                Product product14 = new Product("Majonez", 1, 1, 1);    // id:14
                productDao.insert(product14);
                Product product15 = new Product("Płatki śniadaniowe", 1, 1, 1); // id:15
                productDao.insert(product15);

                ListOfPreferencesDao listOfPreferencesDao = INSTANCE.listOfPreferencesDao();
                listOfPreferencesDao.deleteAll();
                ListOfPreferences listOfPreferences1 = new ListOfPreferences("Moja ulubiona lista");      // id:1
                listOfPreferencesDao.insert(listOfPreferences1);

                DishDao dishDao = INSTANCE.dishDao();
                dishDao.deleteAll();
                Dish dish1 = new Dish("Płatki z mlekiem");      // id:1
                dishDao.insert(dish1);
                Dish dish2 = new Dish("Kurczak z ziemniakami i marchewką");      // id:2
                dishDao.insert(dish2);

                IngredientsOfTheDishDao ingredientsOfTheDishDao = INSTANCE.ingredientsOfTheDishDao();
                ingredientsOfTheDishDao.deleteAll();
                IngredientsOfTheDish ingredientsOfTheDish1 = new IngredientsOfTheDish(15, 1, 35);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish1);
                IngredientsOfTheDish ingredientsOfTheDish2 = new IngredientsOfTheDish(13, 1, (float) 0.150);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish2);
            });
        }
    };
}

package com.example.shoppinglistapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
import com.example.shoppinglistapplication.entities.CompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entities.Dish;
import com.example.shoppinglistapplication.entities.FormOfAccessibility;
import com.example.shoppinglistapplication.entities.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entities.ListOfPreferences;
import com.example.shoppinglistapplication.entities.ListOfPreferencesDish;
import com.example.shoppinglistapplication.entities.Product;
import com.example.shoppinglistapplication.entities.ShoppingList;
import com.example.shoppinglistapplication.entities.UnitOfMeasurement;

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
                            AppRoomDatabase.class, "app_database_wersja01")
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
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CategoryDao categoryDao = INSTANCE.categoryDao();
                categoryDao.deleteAll();

                Category category = new Category("Inne");
                categoryDao.insert(category);

                FormOfAccessibilityDao formOfAccessibilityDao = INSTANCE.formOfAccessibilityDao();
                formOfAccessibilityDao.deleteAll();

                FormOfAccessibility formOfAccessibility = new FormOfAccessibility(100F);
                formOfAccessibilityDao.insert(formOfAccessibility);

                UnitOfMeasurementDao unitOfMeasurementDao = INSTANCE.unitOfMeasurementDao();
                unitOfMeasurementDao.deleteAll();

                UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement("gram");
                unitOfMeasurementDao.insert(unitOfMeasurement);
            });

        }
    };
}

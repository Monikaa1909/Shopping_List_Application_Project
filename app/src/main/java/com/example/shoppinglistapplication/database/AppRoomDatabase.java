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
import com.example.shoppinglistapplication.dao.ProductFormOfAccessibilityDao;
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
import com.example.shoppinglistapplication.entity.ProductFormOfAccessibility;
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
                        CompositionOfTheShoppingList.class,
                        ProductFormOfAccessibility.class},
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
    public abstract ProductFormOfAccessibilityDao productFormOfAccessibilityDao();

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database_03")
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
                Product product1 = new Product("Jabłko", 3, 1);  // id:1
                productDao.insert(product1);
                Product product2 = new Product("Gruszka", 3, 1); // id:2
                productDao.insert(product2);
                Product product3 = new Product("Pomarańcza", 3, 1);  // id:3
                productDao.insert(product3);
                Product product4 = new Product("Banan", 3, 1);   // id:4
                productDao.insert(product4);
                Product product5 = new Product("Malina", 3, 1);  // id:5
                productDao.insert(product5);
                Product product6 = new Product("Marchewka", 2, 1);   // id:6
                productDao.insert(product6);
                Product product7 = new Product("Ziemniak", 2, 1);    // id:7
                productDao.insert(product7);
                Product product8 = new Product("Pomidor", 2, 1); // id:8
                productDao.insert(product8);
                Product product9 = new Product("Kurczak", 4, 1); // id:9
                productDao.insert(product9);
                Product product10 = new Product("Wołowina", 4, 1);   // id:10
                productDao.insert(product10);
                Product product11 = new Product("Chleb", 6, 1);  // id:11
                productDao.insert(product11);
                Product product12 = new Product("Chleb", 6, 1);  // id:12
                productDao.insert(product12);
                Product product13 = new Product("Mleko", 2, 2);  // id:13
                productDao.insert(product13);
                Product product14 = new Product("Majonez", 1, 1);    // id:14
                productDao.insert(product14);
                Product product15 = new Product("Płatki śniadaniowe", 1, 1); // id:15
                productDao.insert(product15);
                Product product16 = new Product("Sos Teriyaki", 1, 2); // id:16
                productDao.insert(product16);
                Product product17 = new Product("Ser gouda", 2, 1); // id:17
                productDao.insert(product17);
                Product product18 = new Product("Makaron", 1, 1); // id:18
                productDao.insert(product18);
                Product product19 = new Product("Sos pomidorowy", 1, 2); // id:19
                productDao.insert(product19);
                Product product20 = new Product("Wieprzowina", 4, 1); // id:20
                productDao.insert(product20);
                Product product21 = new Product("Szynka", 4, 1); // id:21
                productDao.insert(product21);
                Product product22 = new Product("Masło", 5, 1); // id:22
                productDao.insert(product22);
                Product product23 = new Product("Ryż", 1, 1); // id:23
                productDao.insert(product23);
                Product product24 = new Product("Ogórek", 2, 1); // id:24
                productDao.insert(product24);


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
                Dish dish3 = new Dish("Spaghetti");      // id:3
                dishDao.insert(dish3);
                Dish dish4 = new Dish("Sałatka owocowa");      // id:4
                dishDao.insert(dish4);
                Dish dish5 = new Dish("Zapiekanka z szynką i serem");      // id:5
                dishDao.insert(dish5);
                Dish dish6 = new Dish("Kurczak teriyaki z ryżem");      // id:6
                dishDao.insert(dish6);
                Dish dish7 = new Dish("Burger");      // id:7
                dishDao.insert(dish7);
                Dish dish8 = new Dish("Kotlet mielony z puree i ogórkiem");      // id:8
                dishDao.insert(dish8);

                IngredientsOfTheDishDao ingredientsOfTheDishDao = INSTANCE.ingredientsOfTheDishDao();
                ingredientsOfTheDishDao.deleteAll();
                IngredientsOfTheDish ingredientsOfTheDish1 = new IngredientsOfTheDish(15, 1, 35);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish1);
                IngredientsOfTheDish ingredientsOfTheDish2 = new IngredientsOfTheDish(13, 1, (float) 0.150);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish2);
                IngredientsOfTheDish ingredientsOfTheDish3 = new IngredientsOfTheDish(9, 2, 300);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish3);
                IngredientsOfTheDish ingredientsOfTheDish4 = new IngredientsOfTheDish(7, 2, (float) 220.5);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish4);
                IngredientsOfTheDish ingredientsOfTheDish5 = new IngredientsOfTheDish(6, 2, 80);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish5);
                IngredientsOfTheDish ingredientsOfTheDish6 = new IngredientsOfTheDish(20, 3, 420);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish6);
                IngredientsOfTheDish ingredientsOfTheDish7 = new IngredientsOfTheDish(18, 3, 330);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish7);
                IngredientsOfTheDish ingredientsOfTheDish8 = new IngredientsOfTheDish(19, 3, (float) 0.240);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish8);
                IngredientsOfTheDish ingredientsOfTheDish9 = new IngredientsOfTheDish(1, 4, 60);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish9);
                IngredientsOfTheDish ingredientsOfTheDish10 = new IngredientsOfTheDish(2, 4, 40);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish10);
                IngredientsOfTheDish ingredientsOfTheDish11 = new IngredientsOfTheDish(3, 4, 75);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish11);
                IngredientsOfTheDish ingredientsOfTheDish12 = new IngredientsOfTheDish(4, 4, 50);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish12);
                IngredientsOfTheDish ingredientsOfTheDish13 = new IngredientsOfTheDish(12, 5, 120);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish13);
                IngredientsOfTheDish ingredientsOfTheDish14 = new IngredientsOfTheDish(21, 5, 60);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish14);
                IngredientsOfTheDish ingredientsOfTheDish15 = new IngredientsOfTheDish(17, 5, 45);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish15);
                IngredientsOfTheDish ingredientsOfTheDish16 = new IngredientsOfTheDish(9, 6, 350);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish16);
                IngredientsOfTheDish ingredientsOfTheDish17 = new IngredientsOfTheDish(16, 6, (float) 0.80);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish17);
                IngredientsOfTheDish ingredientsOfTheDish18 = new IngredientsOfTheDish(23, 6, 150);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish18);
                IngredientsOfTheDish ingredientsOfTheDish19 = new IngredientsOfTheDish(12, 7, 160);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish19);
                IngredientsOfTheDish ingredientsOfTheDish20 = new IngredientsOfTheDish(10, 7, 220);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish20);
                IngredientsOfTheDish ingredientsOfTheDish21 = new IngredientsOfTheDish(20, 7, 80);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish21);
                IngredientsOfTheDish ingredientsOfTheDish22 = new IngredientsOfTheDish(8, 7, 60);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish22);
                IngredientsOfTheDish ingredientsOfTheDish23 = new IngredientsOfTheDish(17, 7, 70);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish23);
                IngredientsOfTheDish ingredientsOfTheDish24 = new IngredientsOfTheDish(20, 8, 250);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish24);
                IngredientsOfTheDish ingredientsOfTheDish25 = new IngredientsOfTheDish(7, 8, 200);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish25);
                IngredientsOfTheDish ingredientsOfTheDish26 = new IngredientsOfTheDish(24, 8, 55);
                ingredientsOfTheDishDao.insert(ingredientsOfTheDish26);


                ProductFormOfAccessibilityDao productFormOfAccessibilityDao = INSTANCE.productFormOfAccessibilityDao();
                productFormOfAccessibilityDao.deleteAll();
                ProductFormOfAccessibility productFormOfAccessibility1 = new ProductFormOfAccessibility(1, 1);
                productFormOfAccessibilityDao.insert(productFormOfAccessibility1);
                ProductFormOfAccessibility productFormOfAccessibility2 = new ProductFormOfAccessibility(2, 1);
                productFormOfAccessibilityDao.insert(productFormOfAccessibility2);
                ProductFormOfAccessibility productFormOfAccessibility3 = new ProductFormOfAccessibility(3, 1);
                productFormOfAccessibilityDao.insert(productFormOfAccessibility3);
            });
        }
    };
}

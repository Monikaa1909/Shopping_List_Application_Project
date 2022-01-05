package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.OptimizedCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListCheckBox;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.helpfulModel.OptimizedShoppingListDetail;

import java.util.List;

@Dao
public interface CompositionOfTheShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SimpleCompositionOfTheShoppingList composition_of_the_shopping_list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(OptimizedCompositionOfTheShoppingList composition_of_the_shopping_list);

    @Query("DELETE FROM SimpleCompositionOfTheShoppingList")
    void deleteAll();

    @Query("SELECT * FROM SimpleCompositionOfTheShoppingList")
    LiveData<List<SimpleCompositionOfTheShoppingList>> getAllCompositions();

    @Query("UPDATE simplecompositionoftheshoppinglist SET bought = :bought WHERE idShoppingList =:idShoppingList and idProduct =:idProduct")
    void updateSimpleShoppingListBought(long idShoppingList, long idProduct, Boolean bought);

    @Query("UPDATE optimizedcompositionoftheshoppinglist SET bought = :bought WHERE idShoppingList =:idShoppingList and idProduct =:idProduct")
    void updateOptimizedShoppingListBought(long idShoppingList, long idProduct, Boolean bought);

    @Query("select  product.idProduct, product.productName, sum(ingredientsofthedish.quantity * listofpreferencesdish.portions) as quantity, " +
            "unitofmeasurement.unit from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement " +
            "where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and " +
            "listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and\n" +
            "ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            " listofpreferences.idListOfPreferences = :idListOfPreferences group by product.productName ORDER BY product.productName ASC")
    List<ShoppingListDetail> getShoppingListDetailByPreferencesListId(int idListOfPreferences);

    @Query("select  product.idProduct, product.productName, SimpleCompositionOfTheShoppingList.quantity, unitofmeasurement.unit " +
            "from product, SimpleCompositionOfTheShoppingList, unitofmeasurement where SimpleCompositionOfTheShoppingList.idShoppingList = :idShoppingList and " +
            "product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            "product.idProduct = SimpleCompositionOfTheShoppingList.idProduct ORDER BY product.productName ASC")
    List<ShoppingListDetail> getSimpleShoppingListDetailByShoppingListId(int idShoppingList);

    @Query("select  product.idProduct, product.productName, optimizedCompositionOfTheShoppingList.quantity, " +
            "unitofmeasurement.unit, optimizedCompositionOfTheShoppingList.suggestedFormOfAccessibility " +
            "from product, optimizedCompositionOfTheShoppingList, unitofmeasurement where  " +
            "optimizedCompositionOfTheShoppingList.idShoppingList = :idShoppingList and " +
            "product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            "product.idProduct = optimizedCompositionOfTheShoppingList.idProduct ")
    List<OptimizedShoppingListDetail> getOptimizedShoppingListDetailByShoppingListId(int idShoppingList);

    @Query("select  product.idProduct, product.productName, SimpleCompositionOfTheShoppingList.quantity, unitofmeasurement.unit, SimpleCompositionOfTheShoppingList.bought " +
            "from product, SimpleCompositionOfTheShoppingList, unitofmeasurement where SimpleCompositionOfTheShoppingList.idShoppingList = :idShoppingList and " +
            "product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            "product.idProduct = SimpleCompositionOfTheShoppingList.idProduct ORDER BY product.productName ASC")
    List<ShoppingListCheckBox> getSimpleShoppingListCheckBoxByShoppingListId(int idShoppingList);

    @Query("select  product.idProduct, product.productName, optimizedCompositionOfTheShoppingList.quantity, " +
            "unitofmeasurement.unit, optimizedCompositionOfTheShoppingList.bought " +
            "from product, optimizedCompositionOfTheShoppingList, unitofmeasurement where  " +
            "optimizedCompositionOfTheShoppingList.idShoppingList = :idShoppingList and " +
            "product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            "product.idProduct = optimizedCompositionOfTheShoppingList.idProduct ")
    List<ShoppingListCheckBox> getOptimizedShoppingListCheckBoxByShoppingListId(int idShoppingList);

    @Query("select form from productformofaccessibility, formofaccessibility where " +
            "productformofaccessibility.idFormOfAccessibility = formofaccessibility.idFormOfAccessibility " +
            "and productformofaccessibility.idProduct = :idProduct")
    List<Double> getFormsOfAccessibilityByProductId(int idProduct);

    @Query("select type from shoppinglist where idShoppingList = :idShoppingList")
    String getTypeOfShoppingListById(int idShoppingList);

    @Query("select unit from product, unitofmeasurement where product.idUnitOfMeasurement = UnitOfMeasurement.idUnitOfMeasurement and" +
            " product.idProduct = :idProduct ")
    String getUnitOfProduct(int idProduct);

    @Query("select idShoppingList from ShoppingList where shoppingListName = :name limit 1;")
    Integer getIdByShoppingListName(String name);
}
//    select  product.idProduct, product.productName, SimpleCompositionOfTheShoppingList.quantity, unitofmeasurement.unit, SimpleCompositionOfTheShoppingList.suggestedFormOfAccessibility " +
//        "from product, SimpleCompositionOfTheShoppingList, unitofmeasurement where SimpleCompositionOfTheShoppingList.idShoppingList = :idShoppingList and " +
//        "product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
//        "product.idProduct = SimpleCompositionOfTheShoppingList.idProduct ORDER BY product.productName ASC
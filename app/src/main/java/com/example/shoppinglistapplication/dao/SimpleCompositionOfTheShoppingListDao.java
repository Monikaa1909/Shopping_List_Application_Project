package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

@Dao
public interface SimpleCompositionOfTheShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SimpleCompositionOfTheShoppingList composition_of_the_shopping_list);

    @Query("DELETE FROM SimpleCompositionOfTheShoppingList")
    void deleteAll();

    @Query("SELECT * FROM SimpleCompositionOfTheShoppingList")
    LiveData<List<SimpleCompositionOfTheShoppingList>> getAllCompositions();

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
    List<ShoppingListDetail> getShoppingListDetailByShoppingListId(int idShoppingList);
}
//    select  product.idProduct, product.productName, sum(ingredientsofthedish.quantity * listofpreferencesdish.portions) as quantity,
//        unitofmeasurement.unit from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement
//        where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and
//        listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and
//        ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and
//        listofpreferences.idListOfPreferences = 1 group by product.productName ORDER BY product.productName ASC
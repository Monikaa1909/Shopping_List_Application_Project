package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.CompositionOfTheShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

@Dao
public interface CompositionOfTheShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(CompositionOfTheShoppingList composition_of_the_shopping_list);

    @Query("DELETE FROM CompositionOfTheShoppingList")
    void deleteAll();

    @Query("SELECT * FROM CompositionOfTheShoppingList")
    LiveData<List<CompositionOfTheShoppingList>> getAllCompositions();

    @Query("select  product.idProduct, product.productName, sum(ingredientsofthedish.quantity * listofpreferencesdish.portions) as quantity, " +
            "unitofmeasurement.unit from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement " +
            "where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and " +
            "listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and\n" +
            "ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            " listofpreferences.idListOfPreferences = :idListOfPreferences group by product.productName ORDER BY product.productName ASC")
    List<ShoppingListDetail> getShoppingListDetailByPreferencesListId(int idListOfPreferences);

    @Query("select  product.idProduct, product.productName, compositionoftheshoppinglist.quantity, unitofmeasurement.unit " +
            "from product, compositionoftheshoppinglist, unitofmeasurement where compositionoftheshoppinglist.idShoppingList = :idShoppingList and " +
            "product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            "product.idProduct = compositionoftheshoppinglist.idProduct ORDER BY product.productName ASC")
    List<ShoppingListDetail> getShoppingListDetailByShoppingListId(int idShoppingList);
}
//    select  product.idProduct, product.productName, sum(ingredientsofthedish.quantity * listofpreferencesdish.portions) as quantity,
//        unitofmeasurement.unit from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement
//        where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and
//        listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and
//        ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and
//        listofpreferences.idListOfPreferences = 1 group by product.productName ORDER BY product.productName ASC
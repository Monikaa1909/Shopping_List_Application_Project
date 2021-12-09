package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(ShoppingList shopping_list);

    @Query("DELETE FROM ShoppingList")
    void deleteAll();

    @Query("SELECT * FROM ShoppingList ORDER BY shoppingListName ASC")
    LiveData<List<ShoppingList>> getAlphabetizedShoppingLists();

    @Query("select idShoppingList from ShoppingList where shoppingListName = :name limit 1;")
    Integer getIdByShoppingListName(String name);

    @Query("select exists (select * from ShoppingList where shoppingListName = :name)")
    Boolean shoppingListExists(String name);

    @Query("UPDATE ShoppingList SET idShoppingList = :newListName WHERE idShoppingList =:id")
    void updateShoppingListName(long id, String newListName);

    @Query("DELETE FROM ShoppingList WHERE idShoppingList = :id")
    void deleteShoppingListById(long id);

    @Query("select  product.idProduct, product.productName, sum(ingredientsofthedish.quantity * listofpreferencesdish.portions) as quantity, " +
            "unitofmeasurement.unit from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement " +
            "where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and " +
            "listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and\n" +
            "ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            " listofpreferences.idListOfPreferences = :idListOfPreferences group by product.productName ORDER BY product.productName ASC")
    List<ShoppingListDetail> getShoppingListDetail(int idListOfPreferences);
}


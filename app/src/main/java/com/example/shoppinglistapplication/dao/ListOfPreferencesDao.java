package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;

import java.util.List;

@Dao
public interface ListOfPreferencesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListOfPreferences listOfPreferences);

    @Query("DELETE FROM ListOfPreferences")
    void deleteAll();

    @Query("SELECT * FROM ListOfPreferences ORDER BY listOfPreferencesName ASC")
    LiveData<List<ListOfPreferences>> getAlphabetizedListOfPreferences();

    @Query("select idListOfPreferences from listofpreferences where listOfPreferencesName = :name limit 1;")
    Integer getIdByListOfPreferencesName(String name);

    @Query("select listOfPreferencesName from listofpreferences where idListOfPreferences = :id limit 1;")
    String getNameByListOfPreferencesId(int id);

    @Query("select exists (select * from listofpreferences where listOfPreferencesName = :name)")
    Boolean listOfPreferencesExists(String name);

    @Query("UPDATE listofpreferences SET listOfPreferencesName = :newListName WHERE idListOfPreferences =:id")
    void updateListOfPreferencesName(long id, String newListName);

    @Query("DELETE FROM listofpreferences WHERE idListOfPreferences = :id")
    void deleteListOfPreferencesById(long id);

    @Query("select  product.idProduct, product.productName, sum(ingredientsofthedish.quantity * listofpreferencesdish.portions) as quantity, " +
            "unitofmeasurement.unit from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement " +
            "where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and " +
            "listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and\n" +
            "ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and " +
            " listofpreferences.idListOfPreferences = :idListOfPreferences group by product.productName ORDER BY product.productName ASC")
    List<ShoppingListDetail> getShoppingListDetail(int idListOfPreferences);
}


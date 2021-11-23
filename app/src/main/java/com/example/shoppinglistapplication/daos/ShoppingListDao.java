package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.ShoppingList;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ShoppingList shopping_list);

    @Query("DELETE FROM ShoppingList")
    void deleteAll();

    @Query("SELECT * FROM ShoppingList ORDER BY shopping_list_name ASC")
    LiveData<List<ShoppingList>> getAlphabetizedShopping_lists();
}


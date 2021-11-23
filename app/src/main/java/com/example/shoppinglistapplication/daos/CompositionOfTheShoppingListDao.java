package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.CompositionOfTheShoppingList;

import java.util.List;

@Dao
public interface CompositionOfTheShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CompositionOfTheShoppingList composition_of_the_shopping_list);

    @Query("DELETE FROM CompositionOfTheShoppingList")
    void deleteAll();

    @Query("SELECT * FROM CompositionOfTheShoppingList")
    LiveData<List<CompositionOfTheShoppingList>> getAllCompositions();
}

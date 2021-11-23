package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.Dish;

import java.util.List;

@Dao
public interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dish dish);

    @Query("DELETE FROM Dish")
    void deleteAll();

    @Query("SELECT * FROM Dish ORDER BY dish_name ASC")
    LiveData<List<Dish>> getAlphabetizedDishes();
}



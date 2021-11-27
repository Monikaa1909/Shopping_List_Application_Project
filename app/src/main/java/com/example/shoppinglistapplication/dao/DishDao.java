package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Dish;

import java.util.List;

@Dao
public interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Dish dish);

    @Query("DELETE FROM Dish")
    void deleteAll();

    @Query("select idDish from dish where dishName = :name limit 1;")
    Integer getIdByDishName(String name);

    @Query("select exists (select * from dish where dishName = :name)")
    Boolean dishExists(String name);

    @Query("SELECT * FROM Dish ORDER BY dishName ASC")
    LiveData<List<Dish>> getAlphabetizedDishes();
}



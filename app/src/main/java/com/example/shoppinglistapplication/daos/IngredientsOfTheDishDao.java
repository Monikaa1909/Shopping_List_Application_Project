package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.IngredientsOfTheDish;

import java.util.List;

@Dao
public interface IngredientsOfTheDishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IngredientsOfTheDish ingredientsOfTheDish);

    @Query("SELECT * FROM IngredientsOfTheDish")
    LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish();

    @Delete
    void delete(IngredientsOfTheDish ingredientsOfTheDish);

    @Query("DELETE FROM IngredientsOfTheDish")
    void deleteAll();
}


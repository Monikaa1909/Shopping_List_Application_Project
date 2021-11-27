package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;

import java.util.List;

@Dao
public interface IngredientsOfTheDishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(IngredientsOfTheDish ingredientsOfTheDish);

    @Query("SELECT * FROM IngredientsOfTheDish")
    LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish();

    @Delete
    void delete(IngredientsOfTheDish ingredientsOfTheDish);

    @Query("DELETE FROM IngredientsOfTheDish")
    void deleteAll();

    @Query("select product.productName, ingredientsofthedish.quantity from ingredientsofthedish, product, dish where ingredientsofthedish.idDish = dish.idDish and ingredientsofthedish.idProduct = product.idProduct and dishName = :name")
    List<DishDetail> getDetail(String name);
}


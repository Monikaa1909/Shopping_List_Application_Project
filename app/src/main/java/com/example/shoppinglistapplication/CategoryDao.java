package com.example.shoppinglistapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Category category);

    @Query("DELETE FROM Category")
    void deleteAll();

//    @Query("select * from category where category_name = :name")
//    LiveData<Category> getIdByCategoryName(String name);

    @Query("SELECT * FROM category ORDER BY category_name ASC")
    LiveData<List<Category>> getAlphabetizedCategories();
}

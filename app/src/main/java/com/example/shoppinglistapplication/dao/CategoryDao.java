package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Category category);

    @Query("DELETE FROM Category")
    void deleteAll();

    @Query("select idCategory from category where categoryName = :name limit 1;")
    Integer getIdByCategoryName(String name);

    @Query("select exists (select * from category where categoryName = :name)")
    Boolean categoryExists(String name);

    @Query("SELECT * FROM category ORDER BY categoryName ASC")
    LiveData<List<Category>> getAlphabetizedCategories();
}

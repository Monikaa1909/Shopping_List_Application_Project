package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Category category);

    @Query("DELETE FROM Category")
    void deleteAll();

    @Query("select * from category where category_name = :name")
    LiveData<List<Category>> getIdByCategoryName(String name);

    @Query("select id_category from category where category_name = :name limit 1;")
    LiveData<Integer> getIdByCategoryName2(String name);

    @Query("select category_name from category where id_category = :id limit 1;")
    LiveData<String> getCategoryNameById(int id);

    @Query("SELECT * FROM category ORDER BY category_name ASC")
    LiveData<List<Category>> getAlphabetizedCategories();
}

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
    long getIdByCategoryName(String name);

    @Query("select exists (select * from category where categoryName = :name)")
    Boolean categoryExists(String name);

    @Query("UPDATE category SET categoryName = :newCategoryName WHERE idCategory =:id")
    void updateCategoryName(long id, String newCategoryName);

    @Query("DELETE FROM category WHERE idCategory = :id")
    void deleteCategoryById(long id);

    @Query("SELECT * FROM category ORDER BY categoryName ASC")
    LiveData<List<Category>> getAlphabetizedCategories();
}

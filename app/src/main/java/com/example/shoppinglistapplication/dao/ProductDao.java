package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("SELECT * FROM product ORDER BY productName ASC")
    LiveData<List<Product>> getAlphabetizedProducts();

    @Query("SELECT * FROM product, category WHERE product.idCategory = category.idCategory and category.categoryName = :name")
    List<Product> getProductsByCategoryName(String name);

    @Query("select exists (select * from product where productName = :name)")
    Boolean productExists(String name);
}

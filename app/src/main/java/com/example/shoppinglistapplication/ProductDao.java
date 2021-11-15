package com.example.shoppinglistapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("SELECT * FROM product ORDER BY product_name ASC")
    LiveData<List<Product>> getAlphabetizedProducts();

//    @Query("select product_name, category_name from product, category where product.id_category = category.id_category order by product_name ASC")
//    LiveData<List<Product>> getAlphabetizedProductsWithCategorie();
}

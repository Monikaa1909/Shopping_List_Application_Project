package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("SELECT * FROM product ORDER BY productName ASC")
    LiveData<List<Product>> getAlphabetizedProducts();

    @Query("SELECT * FROM product WHERE idCategory = :id ORDER BY productName ASC")
    List<Product> getAlphabetizedProductsByCategory(int id);

    @Query("SELECT * FROM product, category WHERE product.idCategory = category.idCategory and category.categoryName = :name ")
    List<Product> getProductsByCategoryName(String name);

    @Query("SELECT unitOfMeasurement.idUnitOfMeasurement, unitOfMeasurement.unit FROM product, unitofmeasurement WHERE product.idUnitOfMeasurement = unitOfMeasurement.idUnitOfMeasurement and product.idProduct = :id")
    List<UnitOfMeasurement> getProductUnit(int id);

    @Query("SELECT formofaccessibility.idFormOfAccessibility, formofaccessibility.form FROM product, formofaccessibility, productformofaccessibility WHERE product.idProduct = productformofaccessibility.idProduct and productformofaccessibility.idFormOfAccessibility = formofaccessibility.idFormOfAccessibility and product.idProduct = :id")
    List<FormOfAccessibility> getProductForm(int id);

    @Query("SELECT category.idCategory, category.categoryName FROM product, category WHERE product.idCategory = category.idCategory and product.idProduct = :id")
    List<Category> getProductCategory(int id);

    @Query("select exists (select * from product where productName = :name)")
    Boolean productExists(String name);

    @Query("UPDATE product SET productName = :newProductName WHERE idProduct =:id")
    void updateProductName(long id, String newProductName);

    @Query("UPDATE product SET idCategory = :idNewCategory WHERE idProduct =:id")
    void updateProductCategory(long id, long idNewCategory);

    @Query("UPDATE product SET idUnitOfMeasurement = :idNewUnit WHERE idProduct =:id")
    void updateProductUnit(long id, long idNewUnit);

    @Query("DELETE FROM product WHERE idProduct = :id")
    void deleteProductById(long id);
}

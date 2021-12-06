package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.entity.ProductFormOfAccessibility;

import java.util.List;

@Dao
public interface ProductFormOfAccessibilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ProductFormOfAccessibility productFormOfAccessibility);

    @Query("DELETE FROM ProductFormOfAccessibility")
    void deleteAll();

//    @Query("UPDATE productformofaccessibility SET idUnitOfMeasurement = :idNewUnit WHERE idProduct =:id")
//    void updateProductUnit(long id, long idNewUnit);

    @Query("DELETE FROM productformofaccessibility WHERE idProduct = :idProduct and idFormOfAccessibility = :idForm")
    void deleteProductFormOfAccessibilityById(long idProduct, long idForm);

    @Query("SELECT * FROM productformofaccessibility")
    LiveData<List<ProductFormOfAccessibility>> getAllProductFormOfAccessibility();
}

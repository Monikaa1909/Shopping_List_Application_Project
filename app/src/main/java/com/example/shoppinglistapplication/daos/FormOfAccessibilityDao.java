package com.example.shoppinglistapplication.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.FormOfAccessibility;

import java.util.List;

@Dao
public interface FormOfAccessibilityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FormOfAccessibility form_of_accessibility);


    @Query("DELETE FROM FormOfAccessibility")
    void deleteAll();

    @Query("SELECT * FROM FormOfAccessibility")
    LiveData<List<FormOfAccessibility>> getAllForms();
}

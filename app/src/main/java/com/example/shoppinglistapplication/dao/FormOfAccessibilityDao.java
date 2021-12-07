package com.example.shoppinglistapplication.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.FormOfAccessibility;

import java.util.List;

@Dao
public interface FormOfAccessibilityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(FormOfAccessibility form_of_accessibility);

    @Query("DELETE FROM FormOfAccessibility")
    void deleteAll();

    @Query("DELETE FROM formofaccessibility WHERE idFormOfAccessibility = :id")
    void deleteFormById(long id);

    @Query("select exists (select * from formofaccessibility where form = :form)")
    Boolean formExists(float form);

    @Query("select idFormOfAccessibility from formofaccessibility where form = :form")
    long formIdByName(float form);

    @Query("SELECT * FROM FormOfAccessibility")
    LiveData<List<FormOfAccessibility>> getAllForms();
}

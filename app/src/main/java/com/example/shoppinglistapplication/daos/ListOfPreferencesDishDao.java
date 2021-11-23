package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.ListOfPreferencesDish;

import java.util.List;

@Dao
public interface ListOfPreferencesDishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListOfPreferencesDish listOfPreferencesDish);

    @Query("DELETE FROM ListOfPreferencesDish")
    void deleteAll();

    @Query("SELECT * FROM listofpreferencesdish")
    LiveData<List<ListOfPreferencesDish>> getAllListOfPreferences();

}


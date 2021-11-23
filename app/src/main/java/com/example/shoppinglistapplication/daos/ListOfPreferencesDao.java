package com.example.shoppinglistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entities.ListOfPreferences;

import java.util.List;

@Dao
public interface ListOfPreferencesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListOfPreferences listOfPreferences);

    @Query("DELETE FROM ListOfPreferences")
    void deleteAll();

    @Query("SELECT * FROM ListOfPreferences ORDER BY list_of_preferences_name ASC")
    LiveData<List<ListOfPreferences>> getAlphabetizedListOfPreferences();
}


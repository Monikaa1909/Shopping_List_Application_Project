package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.ListOfPreferences;

import java.util.List;

@Dao
public interface ListOfPreferencesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListOfPreferences listOfPreferences);

    @Query("DELETE FROM ListOfPreferences")
    void deleteAll();

    @Query("SELECT * FROM ListOfPreferences ORDER BY listOfPreferencesName ASC")
    LiveData<List<ListOfPreferences>> getAlphabetizedListOfPreferences();

    @Query("select idListOfPreferences from listofpreferences where listOfPreferencesName = :name limit 1;")
    Integer getIdByListOfPreferencesName(String name);

    @Query("select exists (select * from listofpreferences where listOfPreferencesName = :name)")
    Boolean listOfPreferencesExists(String name);

    @Query("UPDATE listofpreferences SET listOfPreferencesName = :newListName WHERE idListOfPreferences =:id")
    void updateListOfPreferencesName(long id, String newListName);

    @Query("DELETE FROM listofpreferences WHERE idListOfPreferences = :id")
    void deleteListOfPreferencesById(long id);
}


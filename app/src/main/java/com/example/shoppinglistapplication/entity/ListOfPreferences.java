package com.example.shoppinglistapplication.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListOfPreferences {

    @PrimaryKey(autoGenerate = true)
    private int idListOfPreferences;

    @NonNull
    private String listOfPreferencesName;

    public int getIdListOfPreferences() {
        return idListOfPreferences;
    }

    public void setIdListOfPreferences(int idListOfPreferences) {
        this.idListOfPreferences = idListOfPreferences;
    }

    @NonNull
    public String getListOfPreferencesName() {
        return listOfPreferencesName;
    }

    public void setListOfPreferencesName(@NonNull String listOfPreferencesName) {
        this.listOfPreferencesName = listOfPreferencesName;
    }

    public ListOfPreferences(@NonNull String listOfPreferencesName) {
        this.listOfPreferencesName = listOfPreferencesName;
    }
}

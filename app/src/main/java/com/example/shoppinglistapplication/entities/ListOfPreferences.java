package com.example.shoppinglistapplication.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListOfPreferences {

    @PrimaryKey(autoGenerate = true)
    private int id_list_of_preferences;

    @NonNull
    private String list_of_preferences_name;

    public int getId_list_of_preferences() {
        return id_list_of_preferences;
    }

    public void setId_list_of_preferences(int id_list_of_preferences) {
        this.id_list_of_preferences = id_list_of_preferences;
    }

    @NonNull
    public String getList_of_preferences_name() {
        return list_of_preferences_name;
    }

    public void setList_of_preferences_name(@NonNull String list_of_preferences_name) {
        this.list_of_preferences_name = list_of_preferences_name;
    }

    public ListOfPreferences(int id_list_of_preferences, @NonNull String list_of_preferences_name) {
        this.id_list_of_preferences = id_list_of_preferences;
        this.list_of_preferences_name = list_of_preferences_name;
    }
}

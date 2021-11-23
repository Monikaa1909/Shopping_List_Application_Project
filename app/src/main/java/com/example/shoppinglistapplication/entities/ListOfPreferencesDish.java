package com.example.shoppinglistapplication.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(primaryKeys =
        {"id_list_of_preferences", "id_dish"},
        foreignKeys = {
                @ForeignKey(
                        entity = Dish.class,
                        parentColumns = "id_dish",
                        childColumns = "id_dish",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = ListOfPreferences.class,
                        parentColumns = "id_list_of_preferences",
                        childColumns = "id_list_of_preferences",
                        onDelete = CASCADE
                )})
public class ListOfPreferencesDish {

    private int id_list_of_preferences;

    private int id_dish;

    private int portions;

    public int getId_list_of_preferences() {
        return id_list_of_preferences;
    }

    public void setId_list_of_preferences(int id_list_of_preferences) {
        this.id_list_of_preferences = id_list_of_preferences;
    }

    public int getId_dish() {
        return id_dish;
    }

    public void setId_dish(int id_dish) {
        this.id_dish = id_dish;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public ListOfPreferencesDish(int id_list_of_preferences, int id_dish, int portions) {
        this.id_list_of_preferences = id_list_of_preferences;
        this.id_dish = id_dish;
        this.portions = portions;
    }
}


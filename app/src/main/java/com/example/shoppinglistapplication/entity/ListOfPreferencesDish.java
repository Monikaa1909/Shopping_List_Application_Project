package com.example.shoppinglistapplication.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(primaryKeys =
        {"idListOfPreferences", "idDish"},
        foreignKeys = {
                @ForeignKey(
                        entity = Dish.class,
                        parentColumns = "idDish",
                        childColumns = "idDish",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = ListOfPreferences.class,
                        parentColumns = "idListOfPreferences",
                        childColumns = "idListOfPreferences",
                        onDelete = CASCADE
                )})
public class ListOfPreferencesDish {


    private int idListOfPreferences;
    private int idDish;
    @NonNull
    private int portions;

    public int getIdListOfPreferences() {
        return idListOfPreferences;
    }
    public void setIdListOfPreferences(int idListOfPreferences) { this.idListOfPreferences = idListOfPreferences; }
    public int getIdDish() {
        return idDish;
    }
    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }
    public int getPortions() {
        return portions;
    }
    public void setPortions(int portions) {
        this.portions = portions;
    }
    public ListOfPreferencesDish(int idListOfPreferences, int idDish, int portions) {
        this.idListOfPreferences = idListOfPreferences;
        this.idDish = idDish;
        this.portions = portions;
    }
}


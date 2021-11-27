package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dish{

    @PrimaryKey(autoGenerate = true)
    private int idDish;

    @NonNull
    private String dishName;

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    @NonNull
    public String getDishName() {
        return dishName;
    }

    public void setDishName(@NonNull String dishName) {
        this.dishName = dishName;
    }

    public Dish(@NonNull String dishName) {
        this.dishName = dishName;
    }
}


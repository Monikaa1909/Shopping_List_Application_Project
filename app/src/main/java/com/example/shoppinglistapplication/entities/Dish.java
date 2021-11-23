package com.example.shoppinglistapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dish {

    @PrimaryKey(autoGenerate = true)
    private int id_dish;

    @NonNull
    private String dish_name;

    public int getId_dish() {
        return id_dish;
    }

    public void setId_dish(int id_dish) {
        this.id_dish = id_dish;
    }

    @NonNull
    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(@NonNull String dish_name) {
        this.dish_name = dish_name;
    }

    public Dish(@NonNull String dish_name, int id_dish) {
        this.dish_name = dish_name;
        this.id_dish = id_dish;
    }
}


package com.example.shoppinglistapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    private int id_shopping_list;

    @NonNull
    private String shopping_list_name;

    public int getId_shopping_list() { return id_shopping_list; }

    public void setId_shopping_list(int id_shopping_list) { this.id_shopping_list = id_shopping_list; }

    @NonNull
    public String getShopping_list_name() { return shopping_list_name; }

    public void setShopping_list_name(@NonNull String shopping_list_name) { this.shopping_list_name = shopping_list_name; }

    public ShoppingList(@NonNull String shopping_list_name) {
        this.shopping_list_name = shopping_list_name;
    }
}


package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    private int idShoppingList;
    @NonNull
    private String shoppingListName;
    @NonNull String type;

    public int getIdShoppingList() { return idShoppingList; }
    public void setIdShoppingList(int idShoppingList) { this.idShoppingList = idShoppingList; }
    @NonNull
    public String getShoppingListName() { return shoppingListName; }
    public void setShoppingListName(@NonNull String shoppingListName) { this.shoppingListName = shoppingListName; }
    @NonNull
    public String getType() { return type; }
    public void setType(@NonNull String type) { this.type = type; }

    public ShoppingList(@NonNull String shoppingListName, @NonNull String type) {
        this.shoppingListName = shoppingListName;
        this.type = type;
    }
}


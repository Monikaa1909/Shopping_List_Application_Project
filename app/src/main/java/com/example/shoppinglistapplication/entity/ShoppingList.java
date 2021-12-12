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

    public int getIdShoppingList() { return idShoppingList; }
    public void setIdShoppingList(int idShoppingList) { this.idShoppingList = idShoppingList; }
    @NonNull
    public String getShoppingListName() { return shoppingListName; }
    public void setShoppingListName(@NonNull String shoppingListName) { this.shoppingListName = shoppingListName; }

    public ShoppingList(@NonNull String shoppingListName) { this.shoppingListName = shoppingListName; }
}


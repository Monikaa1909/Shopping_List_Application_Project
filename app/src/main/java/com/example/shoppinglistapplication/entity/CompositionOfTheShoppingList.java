package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;

public abstract class CompositionOfTheShoppingList {

    @NonNull
    private int idShoppingList;
    @NonNull
    private int idProduct;
    @NonNull
    private double quantity;
    @NonNull
    private String suggestedFormOfAccessibility;
    @NonNull
    private Boolean bought;

    public CompositionOfTheShoppingList() {
        bought = false;
    }

//    public CompositionOfTheShoppingList(@NonNull double quantity, @NonNull int idShoppingList, @NonNull int idProduct, String suggestedFormOfAccessibility) {
//        this.quantity = quantity;
//        this.idProduct = idProduct;
//        this.idShoppingList = idShoppingList;
//        this.suggestedFormOfAccessibility = suggestedFormOfAccessibility;
//    }

    public int getIdShoppingList() {
        return idShoppingList;
    }
    public int getIdProduct(){
        return idProduct;
    }
    public void setIdShoppingList(int idShoppingList) {
        this.idShoppingList = idShoppingList;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    @NonNull
    public double getQuantity() { return quantity; }
    public void setQuantity(@NonNull double quantity) { this.quantity = quantity; }
    public String getSuggestedFormOfAccessibility() { return suggestedFormOfAccessibility; }
    public void setSuggestedFormOfAccessibility(@NonNull String suggestedFormOfAccessibility) { this.suggestedFormOfAccessibility = suggestedFormOfAccessibility; }
    @NonNull
    public Boolean getBought() { return bought; }
    public void setBought(@NonNull Boolean bought) { this.bought = bought; }
}

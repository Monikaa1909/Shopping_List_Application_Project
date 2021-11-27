package com.example.shoppinglistapplication.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"idProduct", "idDish"},
        foreignKeys = {
                @ForeignKey(
                        entity = Product.class,
                        parentColumns = "idProduct",
                        childColumns = "idProduct",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Dish.class,
                        parentColumns = "idDish",
                        childColumns = "idDish",
                        onDelete = CASCADE
                )
        })

public class IngredientsOfTheDish {

    private int idProduct;
    private int idDish;
    private float quantity;

    public float getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getIdDish() {
        return idDish;
    }
    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }
    public IngredientsOfTheDish(int idProduct, int idDish, float quantity) {
        this.idProduct = idProduct;
        this.idDish = idDish;
        this.quantity = quantity;
    }
}
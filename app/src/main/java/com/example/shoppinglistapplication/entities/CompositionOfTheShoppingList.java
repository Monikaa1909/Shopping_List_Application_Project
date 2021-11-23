package com.example.shoppinglistapplication.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id_shopping_list", "id_product"},
        foreignKeys = {
                @ForeignKey(
                        entity = ShoppingList.class,
                        parentColumns = "id_shopping_list",
                        childColumns = "id_shopping_list",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = Product.class,
                        parentColumns = "id_product",
                        childColumns = "id_product",
                        onDelete = CASCADE)})

public class CompositionOfTheShoppingList {

    @NonNull
    private int id_shopping_list;
    @NonNull
    private int id_product;

    @NonNull
    private Integer quantity;
    private String suggested_form_of_accessibility;

    private int id_composition_of_the_shopping_list;

    public int getId_shopping_list() {
        return id_shopping_list;
    }

    public int getId_product(){
        return id_product;
    }

    public void setId_shopping_list(int id_shopping_list) {
        this.id_shopping_list = id_shopping_list;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_composition_of_the_shopping_list() { return id_composition_of_the_shopping_list; }

    public void setId_composition_of_the_shopping_list(int id_composition_of_the_shopping_list) {
        this.id_composition_of_the_shopping_list = id_composition_of_the_shopping_list;
    }

    @NonNull
    public Integer getQuantity() { return quantity; }

    public void setQuantity(@NonNull Integer quantity) { this.quantity = quantity; }

    @NonNull
    public String getSuggested_form_of_accessibility() { return suggested_form_of_accessibility; }

    public void setSuggested_form_of_accessibility(@NonNull String suggested_form_of_accessibility) { this.suggested_form_of_accessibility = suggested_form_of_accessibility; }

    public CompositionOfTheShoppingList(@NonNull Integer quantity, @NonNull String suggested_form_of_accessibility, @NonNull int id_shopping_list, @NonNull int id_product) {
        this.quantity = quantity;
        this.suggested_form_of_accessibility = suggested_form_of_accessibility;
        this.id_product = id_product;
        this.id_shopping_list = id_shopping_list;
    }
}


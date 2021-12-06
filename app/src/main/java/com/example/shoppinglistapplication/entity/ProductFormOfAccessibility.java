package com.example.shoppinglistapplication.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys =
        {"idProduct", "idFormOfAccessibility"},
        foreignKeys = {
                @ForeignKey(
                        entity = Product.class,
                        parentColumns = "idProduct",
                        childColumns = "idProduct",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = FormOfAccessibility.class,
                        parentColumns = "idFormOfAccessibility",
                        childColumns = "idFormOfAccessibility",
                        onDelete = CASCADE
                )})
public class ProductFormOfAccessibility {

    private int idProduct;
    private int idFormOfAccessibility;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdFormOfAccessibility() {
        return idFormOfAccessibility;
    }

    public void setIdFormOfAccessibility(int idFormOfAccessibility) {
        this.idFormOfAccessibility = idFormOfAccessibility;
    }

    public ProductFormOfAccessibility(int idProduct, int idFormOfAccessibility) {
        this.idProduct = idProduct;
        this.idFormOfAccessibility = idFormOfAccessibility;
    }
}

package com.example.shoppinglistapplication.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
                @ForeignKey(
                        entity = Category.class,
                        parentColumns = "idCategory",
                        childColumns = "idCategory",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = UnitOfMeasurement.class,
                        parentColumns = "idUnitOfMeasurement",
                        childColumns = "idUnitOfMeasurement",
                        onDelete = CASCADE),
//                @ForeignKey(
//                        entity = FormOfAccessibility.class,
//                        parentColumns = "idFormOfAccessibility",
//                        childColumns = "idFormOfAccessibility",
//                        onDelete = CASCADE)
})

public class Product {

    @PrimaryKey(autoGenerate = true)
    private int idProduct;

    @NonNull
    private String productName;

    @NonNull
    private int idCategory;

    public int getIdUnitOfMeasurement() {
        return idUnitOfMeasurement;
    }

    public void setIdUnitOfMeasurement(int idUnitOfMeasurement) {
        this.idUnitOfMeasurement = idUnitOfMeasurement;
    }

//    public int getIdFormOfAccessibility() {
//        return idFormOfAccessibility;
//    }
//
//    public void setIdFormOfAccessibility(int idFormOfAccessibility) {
//        this.idFormOfAccessibility = idFormOfAccessibility;
//    }

    @NonNull
    private int idUnitOfMeasurement;

//    @NonNull
//    private int idFormOfAccessibility;

    public int getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    public int getIdProduct() { return idProduct; }
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }
    public String getProductName() { return productName; }
    public void setProductName(@NonNull String productName) { this.productName = productName; }

    public Product(@NonNull String productName, int idCategory, int idUnitOfMeasurement) {
        this.productName = productName;
        this.idCategory = idCategory;
//        this.idFormOfAccessibility = idFormOfAccessibility;
        this.idUnitOfMeasurement = idUnitOfMeasurement;
    }
}

package com.example.shoppinglistapplication;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(foreignKeys = @ForeignKey(entity = Category.class,
                                parentColumns = "id_category",
                                childColumns = "id_category",
                                onDelete = CASCADE))
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id_product;

    @NonNull
    private String product_name;

    @NonNull
    private int id_category;

    public int getId_category() {
        return id_category;
    }
    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
    public int getId_product() { return id_product; }
    public void setId_product(int id_product) { this.id_product = id_product; }
    public String getProduct_name() { return product_name; }
    public void setProduct_name(@NonNull String product_name) { this.product_name = product_name; }

    public Product(@NonNull String product_name, int id_category) {
        this.product_name = product_name;
        this.id_category = id_category;
    }
//    public Product(@NonNull String product_name, int id_category) {
//        this.product_name = product_name;
//        this.id_category = id_category;
//    }
}

package com.example.shoppinglistapplication.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.shoppinglistapplication.entities.Category;

@Entity(foreignKeys = {
                @ForeignKey(
                        entity = Category.class,
                        parentColumns = "id_category",
                        childColumns = "id_category",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = UnitOfMeasurement.class,
                        parentColumns = "id_unit_of_measurement",
                        childColumns = "id_unit_of_measurement",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = FormOfAccessibility.class,
                        parentColumns = "id_form_of_accessibility",
                        childColumns = "id_form_of_accessibility",
                        onDelete = CASCADE)
})

public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id_product;

    @NonNull
    private String product_name;

    @NonNull
    private int id_category;

    public int getId_unit_of_measurement() {
        return id_unit_of_measurement;
    }

    public void setId_unit_of_measurement(int id_unit_of_measurement) {
        this.id_unit_of_measurement = id_unit_of_measurement;
    }

    public int getId_form_of_accessibility() {
        return id_form_of_accessibility;
    }

    public void setId_form_of_accessibility(int id_form_of_accessibility) {
        this.id_form_of_accessibility = id_form_of_accessibility;
    }

    @NonNull
    private int id_unit_of_measurement;
    @NonNull
    private int id_form_of_accessibility;

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

    public Product(@NonNull String product_name, int id_category, int id_form_of_accessibility, int id_unit_of_measurement) {
        this.product_name = product_name;
        this.id_category = id_category;
        this.id_form_of_accessibility = id_form_of_accessibility;
        this.id_unit_of_measurement = id_unit_of_measurement;
    }
//    public Product(@NonNull String product_name, int id_category) {
//        this.product_name = product_name;
//        this.id_category = id_category;
//    }
}

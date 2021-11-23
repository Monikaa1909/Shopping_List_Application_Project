package com.example.shoppinglistapplication.entities;

import static androidx.room.ForeignKey.CASCADE;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"id_product", "id_dish"},
        foreignKeys = {
                @ForeignKey(
                        entity = Product.class,
                        parentColumns = "id_product",
                        childColumns = "id_product",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Dish.class,
                        parentColumns = "id_dish",
                        childColumns = "id_dish",
                        onDelete = CASCADE
                )
        })

public class IngredientsOfTheDish {

    @ColumnInfo(name = "id_product")
    private int id_product;
    @ColumnInfo(name = "id_dish")
    private int id_dish;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_dish() {
        return id_dish;
    }

    public void setId_dish(int id_dish) {
        this.id_dish = id_dish;
    }

    public IngredientsOfTheDish(int id_product, int id_dish) {
        this.id_product = id_product;
        this.id_dish = id_dish;
    }
}
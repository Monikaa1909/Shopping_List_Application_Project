package com.example.shoppinglistapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int id_category;

    @NonNull
    private String category_name;

    public int getId_category() { return id_category; }
    public void setId_category(int id_category) { this.id_category = id_category; }
    public String getCategory_name() { return category_name; }
    public void setCategory_name(@NonNull String category_name) { this.category_name = category_name; }

    public Category(@NonNull String category_name) {
        this.category_name = category_name;
    }
}

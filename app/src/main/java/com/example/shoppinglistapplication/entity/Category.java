package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int idCategory;

    @NonNull
    private String categoryName;

    public int getIdCategory() { return idCategory; }
    public void setIdCategory(int idCategory) { this.idCategory = idCategory; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(@NonNull String categoryName) { this.categoryName = categoryName; }

    public Category(@NonNull String categoryName) {
        this.categoryName = categoryName;
    }
}

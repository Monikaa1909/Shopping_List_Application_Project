package com.example.shoppinglistapplication.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

public class Author implements Serializable {
    @SerializedName("name")
    private String authorName;
    @Override
    public String toString() {
        return authorName;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

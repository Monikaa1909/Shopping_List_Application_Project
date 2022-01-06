package com.example.shoppinglistapplication.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("authors")
    private List<Author> authors;
    @SerializedName("cover_id")
    private String cover;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Author> getAuthors() { return authors; }

    public void setAuthors(List<Author> authors) { this.authors = authors; }

    public String getCover() { return cover; }
    public void setCover(String cover) { this.cover = cover; }
}

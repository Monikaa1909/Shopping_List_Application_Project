package com.example.shoppinglistapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("/subjects/cookbooks.json?details=true")
    Call<BookContainer> findBooks();
}

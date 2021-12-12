package com.example.shoppinglistapplication.helpfulModel;

public class DataValidator {
    public String validateName(String name) {
        String newName =  name.substring(0, 1).toUpperCase() + name.toLowerCase().substring(1);
        return newName;
    }
}

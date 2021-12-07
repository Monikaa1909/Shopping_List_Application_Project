package com.example.shoppinglistapplication.helpfulModel;

public class ListOfPreferencesDetail {

    private int idListOfPreferences;
    private String listOfPreferencesName;
    private int idDish;
    private String dishName;
    private int portions;

    public int getIdListOfPreferences() {
        return idListOfPreferences;
    }

    public void setIdListOfPreferences(int idListOfPreferences) {
        this.idListOfPreferences = idListOfPreferences;
    }

    public String getListOfPreferencesName() {
        return listOfPreferencesName;
    }

    public void setListOfPreferencesName(String listOfPreferencesName) {
        this.listOfPreferencesName = listOfPreferencesName;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }
}

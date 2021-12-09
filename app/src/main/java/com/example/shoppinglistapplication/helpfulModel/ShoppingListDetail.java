package com.example.shoppinglistapplication.helpfulModel;

public class ShoppingListDetail {

//    private int idShoppingList;
//    private String shoppingListName;
    private int idProduct;
    private String productName;
    private float quantity;
    private String unit;

    public ShoppingListDetail() {}

//    public int getIdShoppingList() {
//        return idShoppingList;
//    }
//
//    public void setIdShoppingList(int idShoppingList) {
//        this.idShoppingList = idShoppingList;
//    }
//
//    public String getShoppingListName() {
//        return shoppingListName;
//    }
//
//    public void setShoppingListName(String shoppingListName) {
//        this.shoppingListName = shoppingListName;
//    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

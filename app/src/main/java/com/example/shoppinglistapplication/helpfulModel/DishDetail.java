package com.example.shoppinglistapplication.helpfulModel;

public class DishDetail {
    private String productName;
    private float quantity;
    private String unit;

    public DishDetail() {

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

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() { return unit; }

    public void setUnit(String unit) {this.unit = unit; }
}

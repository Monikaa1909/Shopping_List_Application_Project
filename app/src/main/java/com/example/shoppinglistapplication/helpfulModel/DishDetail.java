package com.example.shoppinglistapplication.helpfulModel;

public class DishDetail {
    private String productName;
    private int idProduct;
    private double quantity;
    private String unit;
    private int idDish;

    public DishDetail() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() { return unit; }

    public void setUnit(String unit) {this.unit = unit; }

    public int getIdDish() { return idDish; }

    public void setIdDish(int idDish) { this.idDish = idDish; }

    public int getIdProduct() { return idProduct; }

    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }
}

package com.example.shoppinglistapplication.builder.productBuilder;

import com.example.shoppinglistapplication.entity.Product;

import java.io.Serializable;

public class SimpleProductBuilder implements IProductBuilder, Serializable {

    private String productName;
    private int idCategory;
    private int idUnitOfMeasurement;

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Override
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    @Override
    public void setIdUnitOfMeasurement(int idUnitOfMeasurement) { this.idUnitOfMeasurement = idUnitOfMeasurement; }

    public String getProductName() {
        return productName;
    }
    public int getIdCategory() { return idCategory; }

    public Product getResult() {
        return new Product(productName, idCategory, idUnitOfMeasurement);
    }
}

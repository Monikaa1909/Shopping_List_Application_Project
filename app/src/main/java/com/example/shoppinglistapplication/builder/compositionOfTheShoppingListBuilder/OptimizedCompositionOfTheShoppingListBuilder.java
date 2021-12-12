package com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder;

import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;

import java.io.Serializable;

public class OptimizedCompositionOfTheShoppingListBuilder implements ICompositionOfTheShoppingListBuilder, Serializable {

    private int idShoppingList;
    private int idProduct;
    private double quantity;

    @Override
    public void setIdShoppingList(int idShoppingList) { this.idShoppingList = idShoppingList; }

    @Override
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    @Override
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public SimpleCompositionOfTheShoppingList getResult() {
        return new SimpleCompositionOfTheShoppingList(quantity, idShoppingList, idProduct, "none");
    }
}

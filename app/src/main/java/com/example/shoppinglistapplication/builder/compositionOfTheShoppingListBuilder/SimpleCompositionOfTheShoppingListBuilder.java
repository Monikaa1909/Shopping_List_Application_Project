package com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder;

import com.example.shoppinglistapplication.entity.OptimizedCompositionOfTheShoppingList;
import com.example.shoppinglistapplication.entity.SimpleCompositionOfTheShoppingList;

import java.io.Serializable;
import java.util.List;

public class SimpleCompositionOfTheShoppingListBuilder implements ICompositionOfTheShoppingListBuilder, Serializable {

    private static SimpleCompositionOfTheShoppingListBuilder INSTANCE;
    private SimpleCompositionOfTheShoppingList simpleCompositionOfTheShoppingList;
    private int idShoppingList;
    private int idProduct;
    private double quantity;
    private String suggestedFormOfAccessibility;

    private SimpleCompositionOfTheShoppingListBuilder() { this.reset(); }

    public static SimpleCompositionOfTheShoppingListBuilder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleCompositionOfTheShoppingListBuilder();
        }
        return INSTANCE;
    }

    @Override
    public void reset() { this.simpleCompositionOfTheShoppingList = new SimpleCompositionOfTheShoppingList(); }

    @Override
    public void setIdShoppingList(int idShoppingList) {
//        this.idShoppingList = idShoppingList;
        simpleCompositionOfTheShoppingList.setIdShoppingList(idShoppingList);
    }

    @Override
    public void setIdProduct(int idProduct) {
        simpleCompositionOfTheShoppingList.setIdProduct(idProduct);
//        this.idProduct = idProduct;
    }

    @Override
    public void setQuantity(double quantity) {
//        this.quantity = quantity;
        simpleCompositionOfTheShoppingList.setQuantity(quantity);
    }

    @Override
    public void setSuggestedFormOfAccessibility(double quantity, List<Double> forms, String unit) {
        simpleCompositionOfTheShoppingList.setSuggestedFormOfAccessibility("brak");
//        this.suggestedFormOfAccessibility = "brak";
    }

    public SimpleCompositionOfTheShoppingList getResult() {
//        suggestedFormOfAccessibility = "none";
        SimpleCompositionOfTheShoppingList result = this.simpleCompositionOfTheShoppingList;
        this.reset();
        return result;
//        return new SimpleCompositionOfTheShoppingList(quantity, idShoppingList, idProduct);
    }
}

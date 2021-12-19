package com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder;

import java.util.List;

public interface ICompositionOfTheShoppingListBuilder {
    void reset();
    void setIdShoppingList(int idShoppingList);
    void setIdProduct(int idProduct);
    void setQuantity(double quantity);
    void setSuggestedFormOfAccessibility(double quantity, List<Double> forms, String unit);
}

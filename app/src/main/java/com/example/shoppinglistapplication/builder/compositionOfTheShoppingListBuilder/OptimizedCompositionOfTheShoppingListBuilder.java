package com.example.shoppinglistapplication.builder.compositionOfTheShoppingListBuilder;

import android.app.Application;
import android.util.Log;

import com.example.shoppinglistapplication.database.AppRoomDatabase;
import com.example.shoppinglistapplication.entity.OptimizedCompositionOfTheShoppingList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OptimizedCompositionOfTheShoppingListBuilder implements ICompositionOfTheShoppingListBuilder, Serializable {

    private static OptimizedCompositionOfTheShoppingListBuilder INSTANCE;
    private OptimizedCompositionOfTheShoppingList optimizedCompositionOfTheShoppingList;

    private OptimizedCompositionOfTheShoppingListBuilder() { this.reset(); }

    public static OptimizedCompositionOfTheShoppingListBuilder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OptimizedCompositionOfTheShoppingListBuilder();
        }
        return INSTANCE;
    }

    @Override
    public void reset() { this.optimizedCompositionOfTheShoppingList = new OptimizedCompositionOfTheShoppingList(); }

    @Override
    public void setIdShoppingList(int idShoppingList) { optimizedCompositionOfTheShoppingList.setIdShoppingList(idShoppingList); }

    @Override
    public void setIdProduct(int idProduct) { optimizedCompositionOfTheShoppingList.setIdProduct(idProduct); }

    @Override
    public void setQuantity(double quantity) { optimizedCompositionOfTheShoppingList.setQuantity(quantity); }

    @Override
    public void setSuggestedFormOfAccessibility(double weight, List<Double> forms, String unit) {

        if (!forms.isEmpty()) {
            double[] packageSizes = new double[forms.size()];
            int s = 0;
            for(Double form: forms) {
                packageSizes[s] = form;
                s++;
            }

            boolean firstAppend = true;
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (weight > 0) {

                if (isLastIndex(packageSizes, i)) {
                    int amount = calculateAmount(weight, packageSizes[i]);
                    append(packageSizes[i], firstAppend, stringBuilder, amount);
                    weight = updateWeight(weight, packageSizes[i] * amount);
                } else {
                    List<Double> calculatedReminders = new ArrayList<>();
                    calculatedReminders.add(calculateRemainder(weight, packageSizes[0], calculateAmount(weight, packageSizes[0])));
                    double minReminder = calculatedReminders.get(0);
                    int minReminderIndex = 0;
                    for (int j = 1; j < packageSizes.length; j++) {
                        double reminder = calculateRemainder(weight, packageSizes[j], calculateAmount(weight, packageSizes[j]));
                        if (reminder == 0) {
                            minReminder = reminder;
                            minReminderIndex = j;
                        } else if (reminder > minReminder) {
                            minReminder = reminder;
                            minReminderIndex = j;
                        }
                        calculatedReminders.add(reminder);
                    }
                    int amount = calculateAmount(weight, packageSizes[minReminderIndex]);
                    append(packageSizes[minReminderIndex], firstAppend, stringBuilder, amount);
                    weight = updateWeight(weight, amount * packageSizes[minReminderIndex]);
                }
            }
            optimizedCompositionOfTheShoppingList.setSuggestedFormOfAccessibility(String.valueOf(stringBuilder) + " " + unit.toUpperCase());
        } else {
            optimizedCompositionOfTheShoppingList.setSuggestedFormOfAccessibility("Brak danych");
        }
    }

    public OptimizedCompositionOfTheShoppingList getResult() {
        OptimizedCompositionOfTheShoppingList result = this.optimizedCompositionOfTheShoppingList;
        this.reset();
        return result;
    }


    private static double updateWeight(double weight, double amount) {
        return weight - amount;
    }

    private static void append(double packageSize, boolean firstAppend, StringBuilder stringBuilder, int amount) {
        if (firstAppend) {
            stringBuilder.append(amount).append("x").append(packageSize);
        } else {
            stringBuilder.append("+").append(amount).append("x").append(packageSize);
        }
    }

    private static boolean isLastIndex(double[] packageSizes, int i) {
        return (i == packageSizes.length - 1);
    }

    static int calculateAmount(double weight, double packageSize) {
        if ( weight % packageSize != 0) {
            return (int) (weight / packageSize + 1);
        }
        else return (int) (weight / packageSize);
    }
    static double calculateRemainder(double weight, double packageSize, int amount) {
        return weight - packageSize * amount;
    }
}

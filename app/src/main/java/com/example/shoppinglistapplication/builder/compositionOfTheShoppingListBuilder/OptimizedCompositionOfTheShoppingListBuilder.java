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

            int leastAmount;
            double smallestReminder;
            boolean firstAppend = true;
            int i = 0;

            List<Double> calculatedReminders = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();


            leastAmount = calculateAmount(weight, packageSizes[0]);
            calculatedReminders.add(calculateRemainder(weight, packageSizes[0], leastAmount));
            smallestReminder = calculatedReminders.get(0);
            while (weight > 0) {

                if (isLastIndex(packageSizes, i)) {
                    int amount = calculateAmount(weight, packageSizes[i]);
                    append(packageSizes[i], firstAppend, stringBuilder, amount);
                    weight -= updateWeight(weight, packageSizes[i] * amount);
                } else {
                    int smallestReminderIndex = 0;
                    for (int j = 0; j < packageSizes.length; j++) {
                        int amount = calculateAmount(weight, packageSizes[j]);
                        double reminder = calculateRemainder(weight, packageSizes[j], amount);
                        if (reminder == 0) {
                            weight -= updateWeight(packageSizes[j], amount);
                            append(packageSizes[j], firstAppend, stringBuilder, amount);
                            continue;
                        } else if (reminder < 0) {
                            if (j != packageSizes.length - 1) {
                                int amountOfSmallerPackage = calculateAmount(weight, packageSizes[j + 1]);
                                if (Math.abs(reminder) > Math.abs(calculateRemainder(weight, packageSizes[j + 1], amountOfSmallerPackage)))
                                    amount--;
                            }
                            if (amount <= 0) {
                                continue;
                            } else {
                                weight -= updateWeight(packageSizes[j], amount);
                                append(packageSizes[j], firstAppend, stringBuilder, amount);
                                stringBuilder.append("+");
                            }
                        } else if (reminder < smallestReminder || (reminder == smallestReminder && leastAmount > amount)) {
                            smallestReminder = reminder;
                            leastAmount = amount;
                            smallestReminderIndex = j;
                        }
                        calculatedReminders.add(reminder);
                    }
                    int amount = calculateAmount(weight, packageSizes[smallestReminderIndex]);

                    weight -= updateWeight(weight, amount * packageSizes[smallestReminderIndex]);
                }
            }

            optimizedCompositionOfTheShoppingList.setSuggestedFormOfAccessibility(optimizedPackagingToString(stringBuilder) + " " + unit.toUpperCase());
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
        return weight * amount;
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

    public static StringBuilder optimizedPackagingToString(StringBuilder stringBuilder) {
        String packaging = stringBuilder.toString();
        String outcome = packaging.substring(0, packaging.length() - 1);
        return new StringBuilder(outcome);
    }
}

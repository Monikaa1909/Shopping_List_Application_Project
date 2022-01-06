package com.example.shoppinglistapplication.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListCheckBox;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.viewholder.ShoppingListCheckBoxViewHolder;
import com.example.shoppinglistapplication.viewholder.ShoppingListDetailViewHolder;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;

public class ShoppingListCheckBoxAdapter extends ListAdapter<ShoppingListCheckBox, ShoppingListCheckBoxViewHolder> {

    private int idShoppingList;

    public ShoppingListCheckBoxAdapter(@NonNull DiffUtil.ItemCallback<ShoppingListCheckBox> diffCallback, int idShoppingList) {
        super(diffCallback);
        this.idShoppingList = idShoppingList;
    }

    @Override
    public ShoppingListCheckBoxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShoppingListCheckBoxViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ShoppingListCheckBoxViewHolder holder, int position) {
        ShoppingListCheckBox current = getItem(position);
        holder.bind(idShoppingList, current.getProductName(), current.getQuantity(), current.getUnit(), current.getBought());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                new Thread(() -> {
                    CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(((Activity)holder.view.getContext()).getApplication());
                    String typeOfShoppingList = compositionOfTheShoppingListViewModel.getTypeOfShoppingListById(idShoppingList);
                    if (isChecked == true) {
                        if (typeOfShoppingList.equals("simple")) {
                            compositionOfTheShoppingListViewModel.updateSimpleShoppingListBought(idShoppingList, current.getIdProduct(), true, emptyFunction -> {});
                        } else {
                            compositionOfTheShoppingListViewModel.updateOptimizedShoppingListBought(idShoppingList, current.getIdProduct(), true, emptyFunction -> {});
                        }
                    } else if (isChecked == false) {
                        if (typeOfShoppingList.equals("simple")) {
                            compositionOfTheShoppingListViewModel.updateSimpleShoppingListBought(idShoppingList, current.getIdProduct(), false, emptyFunction -> {});
                        } else {
                            compositionOfTheShoppingListViewModel.updateOptimizedShoppingListBought(idShoppingList, current.getIdProduct(), false, emptyFunction -> {});
                        }
                    }


//                        Log.d("TYPE", typeOfShoppingList);
                }).start();

            }
        });
    }

    public static class ShoppingListCheckBoxDiff extends DiffUtil.ItemCallback<ShoppingListCheckBox> {

        @Override
        public boolean areItemsTheSame(@NonNull ShoppingListCheckBox oldItem, @NonNull ShoppingListCheckBox newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShoppingListCheckBox oldItem, @NonNull ShoppingListCheckBox newItem) {
            return (oldItem.getProductName().equals(newItem.getProductName()) && oldItem.getUnit().equals(newItem.getUnit()) &&
                    oldItem.getBought().equals(newItem.getBought()));
        }
    }
}
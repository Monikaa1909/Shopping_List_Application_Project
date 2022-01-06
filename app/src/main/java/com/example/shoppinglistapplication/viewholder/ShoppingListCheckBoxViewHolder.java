package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListActivity;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;

public class ShoppingListCheckBoxViewHolder extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    public final CheckBox checkBox;
    private int idShoppingList;
    public View view;

    private ShoppingListCheckBoxViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
//        itemView.setOnClickListener(this);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
        checkBox = itemView.findViewById(R.id.checkbox);
    }

    public void bind(int idShoppingList, String name, double quantity, String unit, Boolean bought) {
        this.idShoppingList = idShoppingList;
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(Math.round(quantity * 100.0) / 100.0) + " " + unit);
        if (bought == true) {
            checkBox.setChecked(true);
        }
//        checkBox.setChecked(true);
    }

    public static ShoppingListCheckBoxViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);
        return new ShoppingListCheckBoxViewHolder(view);
    }


//    @Override
//    public void onClick(View v) {
//        Log.d("TYPE", "click");
//        new Thread(() -> {
//            switch (v.getId()) {
//                case R.id.checkbox:
//                    CheckBox checkBox = v.findViewById(R.id.checkbox);
//                    if (checkBox.isChecked()) {
//                        CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(((Activity)v.getContext()).getApplication());
//                        String typeOfShoppingList = compositionOfTheShoppingListViewModel.getTypeOfShoppingListById(idShoppingList);
//                        Log.d("TYPE", typeOfShoppingList);
//                    } else {
//
//                            CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(((Activity)v.getContext()).getApplication());
//                            String typeOfShoppingList = compositionOfTheShoppingListViewModel.getTypeOfShoppingListById(idShoppingList);
//                            Log.d("TYPE", typeOfShoppingList);
//
//                    }
//
//                    break;
//            }
//        }).start();
//
//    }
}
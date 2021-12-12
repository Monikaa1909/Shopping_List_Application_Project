package com.example.shoppinglistapplication.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;

public class ShoppingListDetailViewHolder extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;

    private ShoppingListDetailViewHolder(View itemView) {
        super(itemView);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
    }

    public void bind(String name, double quantity, String unit) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(Math.round(quantity * 100.0) / 100.0) + " " + unit);
    }

    public static ShoppingListDetailViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_of_details_item, parent, false);
        return new ShoppingListDetailViewHolder(view);
    }
}
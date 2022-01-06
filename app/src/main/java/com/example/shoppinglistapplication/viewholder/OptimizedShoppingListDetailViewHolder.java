package com.example.shoppinglistapplication.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;

public class OptimizedShoppingListDetailViewHolder extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private final TextView dishDetailItemView3;

    private OptimizedShoppingListDetailViewHolder(View itemView) {
        super(itemView);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
        dishDetailItemView3 = itemView.findViewById(R.id.detail_item_suggested_form);
    }

    public void bind(String name, double quantity, String unit, String suggestedFormOfAccessibility) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(Math.round(quantity * 100.0) / 100.0) + " " + unit);
        dishDetailItemView3.setText(suggestedFormOfAccessibility);
    }

    public static OptimizedShoppingListDetailViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_optimized_list_of_details_item, parent, false);
        return new OptimizedShoppingListDetailViewHolder(view);
    }
}
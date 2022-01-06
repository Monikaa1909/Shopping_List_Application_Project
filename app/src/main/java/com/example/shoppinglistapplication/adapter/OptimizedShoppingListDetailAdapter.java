package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.helpfulModel.OptimizedShoppingListDetail;
import com.example.shoppinglistapplication.viewholder.OptimizedShoppingListDetailViewHolder;

public class OptimizedShoppingListDetailAdapter extends ListAdapter<OptimizedShoppingListDetail, OptimizedShoppingListDetailViewHolder> {

    public OptimizedShoppingListDetailAdapter(@NonNull DiffUtil.ItemCallback<OptimizedShoppingListDetail> diffCallback) {
        super(diffCallback);
    }

    @Override
    public OptimizedShoppingListDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OptimizedShoppingListDetailViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(OptimizedShoppingListDetailViewHolder holder, int position) {
        OptimizedShoppingListDetail current = getItem(position);
        holder.bind(current.getProductName(), current.getQuantity(), current.getUnit(), current.getSuggestedFormOfAccessibility());
    }

    public static class ShoppingListDetailWithSuggestedFormDiff extends DiffUtil.ItemCallback<OptimizedShoppingListDetail> {

        @Override
        public boolean areItemsTheSame(@NonNull OptimizedShoppingListDetail oldItem, @NonNull OptimizedShoppingListDetail newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull OptimizedShoppingListDetail oldItem, @NonNull OptimizedShoppingListDetail newItem) {
            return (oldItem.getProductName().equals(newItem.getProductName()) && oldItem.getUnit().equals(newItem.getUnit()));
        }
    }
}

package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.viewholder.ShoppingListDetailViewHolder;

public class ShoppingListDetailAdapter extends ListAdapter<ShoppingListDetail, ShoppingListDetailViewHolder> {

    public ShoppingListDetailAdapter(@NonNull DiffUtil.ItemCallback<ShoppingListDetail> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ShoppingListDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShoppingListDetailViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ShoppingListDetailViewHolder holder, int position) {
        ShoppingListDetail current = getItem(position);
        holder.bind(current.getProductName(), current.getQuantity(), current.getUnit());
    }

    public static class ShoppingListDetailDiff extends DiffUtil.ItemCallback<ShoppingListDetail> {

        @Override
        public boolean areItemsTheSame(@NonNull ShoppingListDetail oldItem, @NonNull ShoppingListDetail newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShoppingListDetail oldItem, @NonNull ShoppingListDetail newItem) {
            return (oldItem.getProductName().equals(newItem.getProductName()) && oldItem.getUnit().equals(newItem.getUnit()));
        }
    }
}

package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;

public class DishDetailAdapter extends ListAdapter<DishDetail, DishDetailViewHolder> {

    public DishDetailAdapter(@NonNull DiffUtil.ItemCallback<DishDetail> diffCallback) {
        super(diffCallback);
    }

    @Override
    public DishDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DishDetailViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(DishDetailViewHolder holder, int position) {
        DishDetail current = getItem(position);
        holder.bind(current.getProductName(), current.getQuantity(), current.getUnit());
    }

    public static class DishDetailDiff extends DiffUtil.ItemCallback<DishDetail> {

        @Override
        public boolean areItemsTheSame(@NonNull DishDetail oldItem, @NonNull DishDetail newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DishDetail oldItem, @NonNull DishDetail newItem) {
            return oldItem.getProductName().equals(newItem.getProductName());
        }
    }
}
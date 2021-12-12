package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.viewholder.DishDetailViewHolder;

public class DishDetailAdapter extends ListAdapter<DishDetail, DishDetailViewHolder> {

    private int version;

    public DishDetailAdapter(@NonNull DiffUtil.ItemCallback<DishDetail> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    @Override
    public DishDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DishDetailViewHolder.create(parent, version);
    }

    @Override
    public void onBindViewHolder(DishDetailViewHolder holder, int position) {
        DishDetail current = getItem(position);
        holder.bind(current.getProductName(), current.getQuantity(), current.getUnit(), current.getIdProduct(), current.getIdDish());
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
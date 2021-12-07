package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.helpfulModel.DishDetail;

public class DishDetailAdapter2 extends ListAdapter<DishDetail, DishDetailViewHolder2> {

    public DishDetailAdapter2(@NonNull DiffUtil.ItemCallback<DishDetail> diffCallback) {
        super(diffCallback);
    }

    @Override
    public DishDetailViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return DishDetailViewHolder2.create(parent);
    }

    @Override
    public void onBindViewHolder(DishDetailViewHolder2 holder, int position) {
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
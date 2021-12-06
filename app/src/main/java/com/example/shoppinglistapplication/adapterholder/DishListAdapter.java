package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Dish;

public class DishListAdapter extends ListAdapter<Dish, DishViewHolder> {

    public DishListAdapter(@NonNull DiffUtil.ItemCallback<Dish> diffCallback) {
        super(diffCallback);
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DishViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        Dish current = getItem(position);
        holder.bind(current.getDishName());
    }

    public static class DishDiff extends DiffUtil.ItemCallback<Dish> {

        @Override
        public boolean areItemsTheSame(@NonNull Dish oldItem, @NonNull Dish newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dish oldItem, @NonNull Dish newItem) {
            return oldItem.getDishName().equals(newItem.getDishName());
        }
    }
}

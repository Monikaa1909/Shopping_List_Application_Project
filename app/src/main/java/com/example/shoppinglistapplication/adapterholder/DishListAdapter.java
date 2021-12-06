package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Dish;

public class DishListAdapter extends ListAdapter<Dish, DishViewHolder> {

    private int version;

    public DishListAdapter(@NonNull DiffUtil.ItemCallback<Dish> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DishViewHolder.create(parent, version);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        Dish current = getItem(position);
        holder.bind(current.getDishName(), current.getIdDish());
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

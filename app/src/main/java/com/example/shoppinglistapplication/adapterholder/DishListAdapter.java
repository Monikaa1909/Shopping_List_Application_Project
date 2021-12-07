package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Dish;

public class DishListAdapter extends ListAdapter<Dish, DishViewHolder> {

    private int version;
    private int idItem;

    public DishListAdapter(@NonNull DiffUtil.ItemCallback<Dish> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    public DishListAdapter(@NonNull DiffUtil.ItemCallback<Dish> diffCallback, int version, int idItem) {
        super(diffCallback);
        this.version = version;
        this.idItem = idItem;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (version == 4 || version == 5) {
            return DishViewHolder.create(parent, version, idItem);
        }
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

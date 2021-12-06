package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.Product;

public class CategoryListAdapter3 extends ListAdapter<Category, CategoryViewHolder3> {

    public CategoryListAdapter3(@NonNull DiffUtil.ItemCallback<Category> diffCallback) {
        super(diffCallback);
    }

    @Override
    public CategoryViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        return CategoryViewHolder3.create(parent);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder3 holder, int position) {
        Category current = getItem(position);
        holder.bind(current.getCategoryName());
    }

    public static class CategoryDiff extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getCategoryName().equals(newItem.getCategoryName());
        }
    }
}

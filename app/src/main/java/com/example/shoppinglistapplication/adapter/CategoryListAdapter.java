package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.itemState.categoryState.ICategoryState;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;

public class CategoryListAdapter extends ListAdapter<Category, CategoryViewHolder> {

    private ICategoryState state;

    public CategoryListAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback, ICategoryState state) {
        super(diffCallback);
        this.state = state;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return state.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category current = getItem(position);
        holder.bind(current.getCategoryName(), current.getIdCategory());
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

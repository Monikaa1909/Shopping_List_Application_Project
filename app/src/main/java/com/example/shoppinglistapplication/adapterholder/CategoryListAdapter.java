package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Category;

public class CategoryListAdapter extends ListAdapter<Category, CategoryViewHolder> {

    private int version;
    private int idItem;
    private String productName;

    public CategoryListAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    public CategoryListAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback, int version, int idItem) {
        super(diffCallback);
        this.version = version;
        this.idItem = idItem;
    }

    public CategoryListAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback, int version, String productName) {
        super(diffCallback);
        this.version = version;
        this.productName = productName;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (version == 3 || version == 7) {
            return CategoryViewHolder.create(parent, version, idItem);
        } else if (version == 6) {
            return CategoryViewHolder.create(parent, version, productName);
        }
        return CategoryViewHolder.create(parent, version);
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

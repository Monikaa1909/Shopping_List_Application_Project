package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.ItemState.ProductState.IProductState;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;

public class ProductListAdapter extends ListAdapter<Product, ProductViewHolder> {

    private IProductState state;

    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback, IProductState state) {
        super(diffCallback);
        this.state = state;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return state.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bind(current.getProductName(), current.getIdProduct());
    }

    public static class ProductDiff extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getProductName().equals(newItem.getProductName());
        }
    }
}
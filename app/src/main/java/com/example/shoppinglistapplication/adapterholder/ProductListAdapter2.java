package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Product;

public class ProductListAdapter2 extends ListAdapter<Product, ProductViewHolder2> {

    private int version;
    private int idItem;

    public ProductListAdapter2(@NonNull DiffUtil.ItemCallback<Product> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    public ProductListAdapter2(@NonNull DiffUtil.ItemCallback<Product> diffCallback, int version, int idItem) {
        super(diffCallback);
        this.version = version;
        this.idItem = idItem;
    }

    @Override
    public ProductViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        if (version == 5 || version == 2 || version == 6) {
            return ProductViewHolder2.create(parent, version, idItem);
        }
        return ProductViewHolder2.create(parent, version);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder2 holder, int position) {
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
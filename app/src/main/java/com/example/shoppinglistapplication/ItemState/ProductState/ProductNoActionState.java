package com.example.shoppinglistapplication.ItemState.ProductState;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;

public class ProductNoActionState implements IProductState {

    @Override
    public void onClick(View v, int idProduct) {

    }

    @Override
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent, this);
    }

    @Override
    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
    }
}

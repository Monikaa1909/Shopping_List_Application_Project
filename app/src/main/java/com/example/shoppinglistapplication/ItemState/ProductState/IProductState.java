package com.example.shoppinglistapplication.ItemState.ProductState;

import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.viewholder.ProductViewHolder;


public interface IProductState {
    public void onClick(View v, int idProduct);
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType);
}

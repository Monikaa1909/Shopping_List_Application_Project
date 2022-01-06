package com.example.shoppinglistapplication.ItemState.CategoryState;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;

public class CategoryNoActionState implements ICategoryState {
    @Override
    public void onClick(View v, int idCategory) {

    }

    @Override
    public CategoryViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return CategoryViewHolder.create(parent, this);
    }

    @Override
    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
    }
}

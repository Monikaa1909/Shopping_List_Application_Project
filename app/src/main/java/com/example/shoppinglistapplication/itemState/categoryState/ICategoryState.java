package com.example.shoppinglistapplication.itemState.categoryState;

import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;

public interface ICategoryState {
    public void onClick(View v, int idCategory);
    public CategoryViewHolder createViewHolder(ViewGroup parent, int viewType);
    public View createView(ViewGroup parent);
}

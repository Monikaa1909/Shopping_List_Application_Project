package com.example.shoppinglistapplication.itemState.categoryState;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;

public class CategoryToShowDetailState implements ICategoryState {

    @Override
    public void onClick(View v, int idCategory) {
        Log.d("AAAAAAAA", "categoryClick");
        Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);
        intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idCategory);
        v.getContext().startActivity(intent);
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

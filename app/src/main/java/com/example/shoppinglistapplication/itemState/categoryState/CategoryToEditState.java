package com.example.shoppinglistapplication.itemState.categoryState;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;

public class CategoryToEditState implements ICategoryState {
    @Override
    public void onClick(View v, int idCategory) {
        Intent intent = new Intent(v.getContext(), EditCategoryActivity.class);
        intent.putExtra(EditCategoryActivity.KEY_EDIT_CATEGORY_ID, idCategory);
        v.getContext().startActivity(intent);
        ((Activity) v.getContext()).finish();
    }

    @Override
    public CategoryViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return CategoryViewHolder.create(parent, this);
    }

    @Override
    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
    }
}

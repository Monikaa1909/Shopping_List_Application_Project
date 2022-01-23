package com.example.shoppinglistapplication.itemState.categoryState;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity2;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity4;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;

public class AddCategoryToIngredientsNewDishState implements ICategoryState {

    private int idAddedInformation;
    public AddCategoryToIngredientsNewDishState(int idAddedInformation) {
        this.idAddedInformation = idAddedInformation;
    }

    @Override
    public void onClick(View v, int idCategory) {
        Intent intent = new Intent(v.getContext(), NewDishActivity4.class);
        intent.putExtra(NewDishActivity2.KEY_NEW_INGREDIENT_CATEGORY_ID, idCategory);
        intent.putExtra(NewDishActivity2.KEY_NEW_DISH_ID, idAddedInformation);
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
                .inflate(R.layout.recyclerview_item, parent, false);
    }
}

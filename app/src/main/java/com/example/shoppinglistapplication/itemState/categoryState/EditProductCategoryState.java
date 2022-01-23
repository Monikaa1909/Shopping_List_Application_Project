package com.example.shoppinglistapplication.itemState.categoryState;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiProducts.ProductCategoryActivity;
import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class EditProductCategoryState implements ICategoryState {

    private int idAddedInformation;
    public EditProductCategoryState(int idAddedInformation) {
        this.idAddedInformation = idAddedInformation;
    }

    @Override
    public void onClick(View v, int idCategory) {
        ProductViewModel productViewModel = new ProductViewModel(((Activity) v.getContext()).getApplication());
        new Thread(() -> {
            productViewModel.updateProductCategory(idAddedInformation, idCategory, emptyFunction -> {
            });
            Intent intent = new Intent(v.getContext(), ProductCategoryActivity.class);
            intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idAddedInformation);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }).start();
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

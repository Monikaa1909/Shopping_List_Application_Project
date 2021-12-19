package com.example.shoppinglistapplication.ItemState.ProductState;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class ProductByCategoryToDeleteState implements IProductState {

    private int idCategory;

    public ProductByCategoryToDeleteState(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public void onClick(View v, int idProduct) {
        ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
        new Thread(() -> {
            productViewModel.deleteProductById(idProduct, emptyFunction -> {});
            Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);
            intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idCategory);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        }).start();
    }

    @Override
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent, this);
    }
}

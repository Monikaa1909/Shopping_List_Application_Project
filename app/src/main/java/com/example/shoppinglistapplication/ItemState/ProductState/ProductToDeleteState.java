package com.example.shoppinglistapplication.ItemState.ProductState;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class ProductToDeleteState implements IProductState{

    @Override
    public void onClick(View v, int idProduct) {
        ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
        new Thread(() -> {
            productViewModel.deleteProductById(idProduct, emptyFunction -> {});
            Intent intent = new Intent(v.getContext(), ProductsActivity.class);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        }).start();
    }

    @Override
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent, this);
    }

    @Override
    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
    }
}

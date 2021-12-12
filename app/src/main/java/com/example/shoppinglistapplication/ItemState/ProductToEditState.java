package com.example.shoppinglistapplication.ItemState;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.uiProducts.EditProductActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class ProductToEditState implements IProductState{

    @Override
    public void onClick(View v, int idProduct) {
        Intent intent = new Intent(v.getContext(), EditProductActivity.class);
        intent.putExtra(EditProductActivity.KEY_EDIT_PRODUCT_ID, idProduct);
        v.getContext().startActivity(intent);
        ((Activity)v.getContext()).finish();
    }

    @Override
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent, this);
    }
}

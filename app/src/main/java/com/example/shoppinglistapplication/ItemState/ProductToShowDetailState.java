package com.example.shoppinglistapplication.ItemState;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;

public class ProductToShowDetailState implements IProductState {

    @Override
    public void onClick(View v, int idProduct) {
        Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
        v.getContext().startActivity(intent);
    }

    @Override
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent, this);
    }
}

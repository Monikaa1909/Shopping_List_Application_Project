package com.example.shoppinglistapplication.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.ItemState.ProductState.IProductState;
import com.example.shoppinglistapplication.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView productItemView;
    private int idProduct;
    private IProductState state = null;

    private ProductViewHolder(View itemView, IProductState state) {
        super(itemView);
        itemView.setOnClickListener(this);
        productItemView = itemView.findViewById(R.id.item_name);
        this.state = state;
    }

    public void bind(String text, int idProduct) {
        productItemView.setText(text);
        this.idProduct = idProduct;
    }

    public static ProductViewHolder create(ViewGroup parent, IProductState state) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(view, state);
    }

    @Override
    public void onClick(View v) {
        // WZORZEC STATE
        state.onClick(v, idProduct);
    }
}

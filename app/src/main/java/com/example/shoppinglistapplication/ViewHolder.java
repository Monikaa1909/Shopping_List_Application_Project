package com.example.shoppinglistapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ProductViewHolder extends RecyclerView.ViewHolder {

    private final TextView productItemView;

    private ProductViewHolder(View itemView) {
        super(itemView);
        productItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        productItemView.setText(text);
    }

    static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(view);
    }
}

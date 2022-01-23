package com.example.shoppinglistapplication.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.itemState.categoryState.ICategoryState;
import com.example.shoppinglistapplication.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView categoryItemView;
    private int idCategory;
    private ICategoryState state = null;

    private CategoryViewHolder(View itemView, ICategoryState state) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryItemView = itemView.findViewById(R.id.item_name);
        this.state = state;
    }

    public void bind(String text, int idCategory) {
        categoryItemView.setText(text);
        this.idCategory = idCategory;
    }

    public static CategoryViewHolder create(ViewGroup parent, ICategoryState state) {
        View view = state.createView(parent);
        return new CategoryViewHolder(view, state);
    }

    @Override
    public void onClick(View v) {

        // WZORZEC STATE
        state.onClick(v, idCategory);
    }
}

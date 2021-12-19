package com.example.shoppinglistapplication.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.ItemState.CategoryState.ICategoryState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder(view, state);
    }

    @Override
    public void onClick(View v) {

        // WZORZEC STATE
        state.onClick(v, idCategory);
    }
}

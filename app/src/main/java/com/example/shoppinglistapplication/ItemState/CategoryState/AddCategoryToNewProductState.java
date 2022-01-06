package com.example.shoppinglistapplication.ItemState.CategoryState;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity3;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;

public class AddCategoryToNewProductState implements ICategoryState {

    private SimpleProductBuilder simpleProductBuilder;

    public AddCategoryToNewProductState(SimpleProductBuilder builder) {
        this.simpleProductBuilder = builder;
    }

    @Override
    public void onClick(View v, int idCategory) {
        Intent intent = new Intent(v.getContext(), NewProductActivity3.class);
        simpleProductBuilder.setIdCategory(idCategory);
        intent.putExtra("builder", simpleProductBuilder);
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

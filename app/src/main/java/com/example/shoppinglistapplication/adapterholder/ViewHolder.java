package com.example.shoppinglistapplication.adapterholder;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.ProductsByDishesActivity;
import com.example.shoppinglistapplication.R;

class ProductViewHolder extends RecyclerView.ViewHolder {

    private final TextView productItemView;

    private ProductViewHolder(View itemView) {
        super(itemView);
        productItemView = itemView.findViewById(R.id.product_textView);
    }

    public void bind(String text) {
        productItemView.setText(text);
    }

    public static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_products_item, parent, false);
        return new ProductViewHolder(view);
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String KEY_CATEGORY_NAME = "categoryName";
    private final TextView categoryItemView;

    private CategoryViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryItemView = itemView.findViewById(R.id.category_item_name);
    }

    public void bind(String text) {
        categoryItemView.setText(text);
    }

    public static CategoryViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_categories_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);

        intent.putExtra(KEY_CATEGORY_NAME, categoryItemView.getText());
        v.getContext().startActivity(intent);
    }
}


class PreferencesViewHolder extends RecyclerView.ViewHolder {

    private final TextView preferencesItemView;

    private PreferencesViewHolder(View itemView) {
        super(itemView);
        preferencesItemView = itemView.findViewById(R.id.preferences_textView);
    }

    public void bind(String text) {
        preferencesItemView.setText(text);
    }

    public static PreferencesViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_lists_item, parent, false);
        return new PreferencesViewHolder(view);
    }
}

class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String KEY_DISH_NAME = "dishName";
    private final TextView dishItemView;

    private DishViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishItemView = itemView.findViewById(R.id.dish_item_name);
    }

    public void bind(String text) {
        dishItemView.setText(text);
    }

    public static DishViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dishes_item, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        Log.d("CAT", "klik w danie");
        Intent intent = new Intent(v.getContext(), ProductsByDishesActivity.class);
        intent.putExtra(KEY_DISH_NAME, dishItemView.getText());
        v.getContext().startActivity(intent);
    }
}

class DishDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//    private static final String KEY_DISH_DETAIL = "dishDetail";
    private final TextView dishDetailItemView;

    private DishDetailViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishDetailItemView = itemView.findViewById(R.id.dish_detail_item_name);
    }

    public void bind(String text) {
        dishDetailItemView.setText(text);
    }

    public static DishDetailViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dishes_details_item, parent, false);
        return new DishDetailViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        Log.d("CAT", "klik w dishdetail");
//        Intent intent = new Intent(v.getContext(), ProductsByDishesActivity.class);
//        intent.putExtra(KEY_DISH_NAME, dishItemView.getText());
//        v.getContext().startActivity(intent);
    }
}

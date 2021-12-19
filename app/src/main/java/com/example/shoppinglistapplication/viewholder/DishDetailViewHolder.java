package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiDishes.EditQuantityActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;

public class DishDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private int version;
    private int idProduct;
    private int idDish;

    private DishDetailViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
        this.version = version;
    }

    public void bind(String name, double quantity, String unit, int idProduct, int idDish) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(Math.round(quantity * 100.0) / 100.0) + " " + unit);
        this.idProduct = idProduct;
        this.idDish = idDish;
    }

    public static DishDetailViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_of_details_item, parent, false);
        return new DishDetailViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) {  // usuwanie składnika
            IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                ingredientsOfTheDishViewModel.deleteIngredientOfTheDishById(idProduct, idDish, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), IngredientsDishActivity.class);
                intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 2) {  // edytowanie składnika
            IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                Intent intent = new Intent(v.getContext(), EditQuantityActivity.class);
                intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
                intent.putExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_ID, idProduct);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
    }
}
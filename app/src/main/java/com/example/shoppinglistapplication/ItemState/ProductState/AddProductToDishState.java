package com.example.shoppinglistapplication.ItemState.ProductState;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity3;
import com.example.shoppinglistapplication.viewholder.ProductViewHolder;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import java.util.List;

public class AddProductToDishState implements IProductState {

    private int idDish;

    public AddProductToDishState(int idDish) {
        this.idDish = idDish;
    }

    @Override
    public void onClick(View v, int idProduct) {
        ProductViewModel productViewModel = new ProductViewModel(((Activity) v.getContext()).getApplication());
        new Thread(() -> {
            List<UnitOfMeasurement> units = productViewModel.getProductUnit(idProduct);
            String unit = units.get(0).getUnit();
            Intent intent = new Intent(v.getContext(), NewDishDetailActivity3.class);
            intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
            intent.putExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_ID, idProduct);
            intent.putExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_UNIT, unit);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }).start();
    }

    @Override
    public ProductViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent, this);
    }
}

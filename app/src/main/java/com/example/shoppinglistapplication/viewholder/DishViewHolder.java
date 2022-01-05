package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity2;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity4;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfThePreferencesDetailActivity2;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;

public class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView dishItemView;
    private int version;
    private int idDish;
    private int idItem;
    private String dishName;

    private DishViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private DishViewHolder(View itemView, int version, int idItem) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
        this.idItem = idItem;
    }

    public void bind(String text, int idDish) {
        dishItemView.setText(text);
        this.idDish = idDish;
        this.dishName = text;
    }

    public static DishViewHolder create(ViewGroup parent, int version) {
        if (version == 2 || version == 3) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
            return new DishViewHolder(view, version);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DishViewHolder(view, version);
    }

    public static DishViewHolder create(ViewGroup parent, int version, int idItem) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DishViewHolder(view, version, idItem);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // wyświetlenie składników dania
            Intent intent = new Intent(v.getContext(), IngredientsDishActivity.class);
            intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
            v.getContext().startActivity(intent);
//            ((Activity)v.getContext()).finish();

        } else if (version == 2) {  // usuwanie dania
            DishViewModel dishViewModel = new DishViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć danie?")
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                dishViewModel.deleteDishById(idDish, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), DishesActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();
                        }
                    })
                    .setNegativeButton("Anuluj usuwanie dania", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), DishesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 3) {  // edytowanie kategorii
            Intent intent = new Intent(v.getContext(), EditDishActivity.class);
            intent.putExtra(EditDishActivity.KEY_EDIT_DISH_ID, idDish);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();

        } else if (version == 4) {  // wybór dania do listy preferencji
            Intent intent = new Intent(v.getContext(), NewListOfThePreferencesDetailActivity2.class);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idItem);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_DISH_ID, idDish);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_DISH_NAME, dishName);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();

        } else if (version == 5) {  // wybór dania przy tworzeniu listy preferencji
            Intent intent = new Intent(v.getContext(), NewListOfPreferencesActivity4.class);
            intent.putExtra(NewListOfPreferencesActivity2.KEY_NEW_DISH_IN_LIST_OF_PREFERENCES_ID, idDish);
            intent.putExtra(NewListOfPreferencesActivity2.KEY_NEW_LIST_OF_PREFERENCES_ID, idItem);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        }
    }
}
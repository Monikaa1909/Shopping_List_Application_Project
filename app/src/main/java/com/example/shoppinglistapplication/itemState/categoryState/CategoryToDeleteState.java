package com.example.shoppinglistapplication.itemState.categoryState;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.viewholder.CategoryViewHolder;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;

public class CategoryToDeleteState implements ICategoryState {
    @Override
    public void onClick(View v, int idCategory) {
        CategoryViewModel categoryViewModel = new CategoryViewModel(((Activity) v.getContext()).getApplication());

        AlertDialog.Builder builder = new AlertDialog.Builder((Activity) v.getContext())
                .setView(((Activity) v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                .setTitle("Czy na pewno chcesz usunąć kategorię?")
                .setMessage(R.string.lose_your_category_data)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new Thread(() -> {
                            categoryViewModel.deleteCategoryById(idCategory, emptyFunction -> {
                            });
                            Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity) v.getContext()).finish();
                        }).start();

                    }
                })
                .setNegativeButton("Anuluj usuwanie kategorii", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                        v.getContext().startActivity(intent);
                        ((Activity) v.getContext()).finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public CategoryViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return CategoryViewHolder.create(parent, this);
    }

    @Override
    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
    }

}

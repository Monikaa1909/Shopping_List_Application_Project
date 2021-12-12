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
import com.example.shoppinglistapplication.uiShoppingList.EditShoppingListActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListDetailActivity;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView shoppingListItemView;
    private int version;
    private int idShoppingList;

    private ShoppingListViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        shoppingListItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    public void bind(String text, int idShoppingList) {
        shoppingListItemView.setText(text);
        this.idShoppingList = idShoppingList;
    }

    public static ShoppingListViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ShoppingListViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // wyświetlanie zawartości listy
            Intent intent = new Intent(v.getContext(), ShoppingListDetailActivity.class);
            intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID, idShoppingList);
            v.getContext().startActivity(intent);
        }
        else if (version == 2) { // usuniecie listy
            ShoppingListViewModel shoppingListViewModel = new ShoppingListViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć listę?")
                    .setMessage(R.string.lose_your_shopping_list_data)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                shoppingListViewModel.deleteShoppingListById(idShoppingList, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), ShoppingListActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();
                        }
                    })
                    .setNegativeButton("Anuluj usuwanie listy", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), ShoppingListActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 3) {  // edytowanie listy
            Intent intent = new Intent(v.getContext(), EditShoppingListActivity.class);
            intent.putExtra(EditShoppingListActivity.KEY_EDIT_SHOPPING_LIST_ID, idShoppingList);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }
    }
}

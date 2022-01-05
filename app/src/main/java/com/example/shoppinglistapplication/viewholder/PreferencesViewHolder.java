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
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.EditListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;

public class PreferencesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView preferencesItemView;
    private int version;
    private int idListOfPreferences;

    private PreferencesViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        preferencesItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    public void bind(String text, int idListOfPreferences) {
        preferencesItemView.setText(text);
        this.idListOfPreferences = idListOfPreferences;
    }

    public static PreferencesViewHolder create(ViewGroup parent, int version) {
        if (version == 2 || version == 3) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
            return new PreferencesViewHolder(view, version);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PreferencesViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // wyświetlanie zawartości listy
            Intent intent = new Intent(v.getContext(), CompositionListOfThePreferencesActivity.class);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        } else if (version == 2) { // usuniecie listy
            ListOfPreferencesViewModel listOfPreferencesViewModel = new ListOfPreferencesViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć listę?")
                    .setMessage(R.string.lose_your_list_of_preferences_data)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                listOfPreferencesViewModel.deleteListOfPreferencesById(idListOfPreferences, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), ListsOfPreferencesActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();
                        }
                    })
                    .setNegativeButton("Anuluj usuwanie listy", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), ListsOfPreferencesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 3) {  // edytowanie listy
            Intent intent = new Intent(v.getContext(), EditListOfPreferencesActivity.class);
            intent.putExtra(EditListOfPreferencesActivity.KEY_EDIT_LIST_OF_PREFERENCES_ID, idListOfPreferences);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }
    }
}
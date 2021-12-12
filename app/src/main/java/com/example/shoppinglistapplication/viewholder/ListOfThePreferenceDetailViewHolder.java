package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.EditPortionsActivity;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;

public class ListOfThePreferenceDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private int version;
    private int portions;
    private int idDish;
    private int idListOfPreferences;
    private String dishName;
    private String listOfPreferencesName;

    private ListOfThePreferenceDetailViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
        this.version = version;
    }

    public void bind(int idDish, String dishName, int idListOfPreferences, String listOfPreferencesName, int portions) {
        dishDetailItemView.setText(dishName);
        dishDetailItemView2.setText(String.valueOf(portions));

        this.idListOfPreferences = idListOfPreferences;
        this.idDish = idDish;
        this.dishName = dishName;
        this.listOfPreferencesName = listOfPreferencesName;
        this.portions = portions;
    }

    public static ListOfThePreferenceDetailViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_of_details_item, parent, false);
        return new ListOfThePreferenceDetailViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) {  // usuwanie dania z listy
            ListOfPreferencesDishViewModel listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                listOfPreferencesDishViewModel.deleteListOfPreferencesDish(idDish, idListOfPreferences, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), CompositionListOfThePreferencesActivity.class);
                intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
        else if (version == 2) {  // edytowanie składnika
            ListOfPreferencesDishViewModel listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                Intent intent = new Intent(v.getContext(), EditPortionsActivity.class);
                intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
                intent.putExtra(CompositionListOfThePreferencesActivity.KEY_DISH_ID, idDish);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
    }
}
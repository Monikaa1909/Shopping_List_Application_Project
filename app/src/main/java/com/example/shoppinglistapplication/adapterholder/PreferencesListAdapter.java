package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.ListOfPreferences;

public class PreferencesListAdapter extends ListAdapter<ListOfPreferences, PreferencesViewHolder> {

    public PreferencesListAdapter(@NonNull DiffUtil.ItemCallback<ListOfPreferences> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PreferencesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PreferencesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(PreferencesViewHolder holder, int position) {
        ListOfPreferences current = getItem(position);
        holder.bind(current.getListOfPreferencesName());
    }

    public static class PreferencesDiff extends DiffUtil.ItemCallback<ListOfPreferences> {

        @Override
        public boolean areItemsTheSame(@NonNull ListOfPreferences oldItem, @NonNull ListOfPreferences newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListOfPreferences oldItem, @NonNull ListOfPreferences newItem) {
            return oldItem.getListOfPreferencesName().equals(newItem.getListOfPreferencesName());
        }
    }
}
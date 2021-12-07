package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;

public class ListOfThePreferenceDetailAdapter extends ListAdapter<ListOfPreferencesDetail, ListOfThePreferenceDetailViewHolder> {

    private int version;

    public ListOfThePreferenceDetailAdapter(@NonNull DiffUtil.ItemCallback<ListOfPreferencesDetail> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    @Override
    public ListOfThePreferenceDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListOfThePreferenceDetailViewHolder.create(parent, version);
    }

    @Override
    public void onBindViewHolder(ListOfThePreferenceDetailViewHolder holder, int position) {
        ListOfPreferencesDetail current = getItem(position);
        holder.bind(current.getIdDish(), current.getDishName(), current.getIdListOfPreferences(), current.getListOfPreferencesName(), current.getPortions());
    }

    public static class ListOfThePreferenceDetailDiff extends DiffUtil.ItemCallback<ListOfPreferencesDetail> {

        @Override
        public boolean areItemsTheSame(@NonNull ListOfPreferencesDetail oldItem, @NonNull ListOfPreferencesDetail newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListOfPreferencesDetail oldItem, @NonNull ListOfPreferencesDetail newItem) {
            return (oldItem.getDishName().equals(newItem.getDishName()) && oldItem.getListOfPreferencesName().equals(newItem.getListOfPreferencesName()));
        }
    }
}
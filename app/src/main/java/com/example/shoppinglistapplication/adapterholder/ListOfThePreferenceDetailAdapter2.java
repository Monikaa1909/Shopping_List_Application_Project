package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;

public class ListOfThePreferenceDetailAdapter2 extends ListAdapter<ListOfPreferencesDetail, ListOfThePreferenceDetailViewHolder2> {

    public ListOfThePreferenceDetailAdapter2(@NonNull DiffUtil.ItemCallback<ListOfPreferencesDetail> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ListOfThePreferenceDetailViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListOfThePreferenceDetailViewHolder2.create(parent);
    }

    @Override
    public void onBindViewHolder(ListOfThePreferenceDetailViewHolder2 holder, int position) {
        ListOfPreferencesDetail current = getItem(position);
        holder.bind(current.getDishName(), current.getPortions());
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

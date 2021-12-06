package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;

public class UnitOfMeasurementListAdapter extends ListAdapter<UnitOfMeasurement, UnitOfMeasurementViewHolder> {

    public UnitOfMeasurementListAdapter(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback) {
        super(diffCallback);
    }

    @Override
    public UnitOfMeasurementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UnitOfMeasurementViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(UnitOfMeasurementViewHolder holder, int position) {
        UnitOfMeasurement current = getItem(position);
        holder.bind(current.getUnit());
    }

    public static class UnitOfMeasurementDiff extends DiffUtil.ItemCallback<UnitOfMeasurement> {

        @Override
        public boolean areItemsTheSame(@NonNull UnitOfMeasurement oldItem, @NonNull UnitOfMeasurement newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UnitOfMeasurement oldItem, @NonNull UnitOfMeasurement newItem) {
            return oldItem.getUnit().equals(newItem.getUnit());
        }
    }
}
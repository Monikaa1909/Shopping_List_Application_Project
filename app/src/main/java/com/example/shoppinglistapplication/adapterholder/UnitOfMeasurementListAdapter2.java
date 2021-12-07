package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.UnitOfMeasurement;

public class UnitOfMeasurementListAdapter2 extends ListAdapter<UnitOfMeasurement, UnitOfMeasurementViewHolder2> {

    public String productName;
    public int idCategory;
    private int idProduct;
    private int version;

    public UnitOfMeasurementListAdapter2(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version, String productName, int idCategory) {
        super(diffCallback);
        this.productName = productName;
        this.idCategory = idCategory;
        this.version = version;
    }

    public UnitOfMeasurementListAdapter2(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version, int idProduct) {
        super(diffCallback);
        this.idProduct = idProduct;
        this.version = version;
    }

    public UnitOfMeasurementListAdapter2(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    @Override
    public UnitOfMeasurementViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        if (version == 3) {
            return UnitOfMeasurementViewHolder2.create(parent, version, idProduct);
        } else if (version == 4) {
            return UnitOfMeasurementViewHolder2.create(parent, version);
        }
        return UnitOfMeasurementViewHolder2.create(parent, version, productName, idCategory);
    }

    @Override
    public void onBindViewHolder(UnitOfMeasurementViewHolder2 holder, int position) {
        UnitOfMeasurement current = getItem(position);
        holder.bind(current.getUnit(), current.getIdUnitOfMeasurement());
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
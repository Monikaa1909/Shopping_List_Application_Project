package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.viewholder.UnitOfMeasurementViewHolder;

public class UnitOfMeasurementListAdapter extends ListAdapter<UnitOfMeasurement, UnitOfMeasurementViewHolder> {

    public String productName;
    public int idCategory;
    private int idProduct;
    private int version;
    private SimpleProductBuilder simpleProductBuilder;

//    public UnitOfMeasurementListAdapter(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version, String productName, int idCategory) {
//        super(diffCallback);
//        this.productName = productName;
//        this.idCategory = idCategory;
//        this.version = version;
//    }

    public UnitOfMeasurementListAdapter(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version, SimpleProductBuilder simpleProductBuilder) {
        super(diffCallback);
        this.simpleProductBuilder = simpleProductBuilder;
        this.version = version;
    }

    public UnitOfMeasurementListAdapter(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version, int idProduct) {
        super(diffCallback);
        this.idProduct = idProduct;
        this.version = version;
    }

    public UnitOfMeasurementListAdapter(@NonNull DiffUtil.ItemCallback<UnitOfMeasurement> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    @Override
    public UnitOfMeasurementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (version == 3) {
            return UnitOfMeasurementViewHolder.create(parent, version, idProduct);
        } else if (version == 4) {
            return UnitOfMeasurementViewHolder.create(parent, version);
        }
//        return UnitOfMeasurementViewHolder.create(parent, version, productName, idCategory);
        return UnitOfMeasurementViewHolder.create(parent, version, simpleProductBuilder);
    }


    @Override
    public void onBindViewHolder(UnitOfMeasurementViewHolder holder, int position) {
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
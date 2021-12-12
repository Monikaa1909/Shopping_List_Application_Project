package com.example.shoppinglistapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.viewholder.FormOfAccessibilityViewHolder;

public class FormOfAccessibilityListAdapter extends ListAdapter<FormOfAccessibility, FormOfAccessibilityViewHolder> {

    private int idProduct;
    private int version;

    public FormOfAccessibilityListAdapter(@NonNull DiffUtil.ItemCallback<FormOfAccessibility> diffCallback, int version) {
        super(diffCallback);
        this.version = version;
    }

    public FormOfAccessibilityListAdapter(@NonNull DiffUtil.ItemCallback<FormOfAccessibility> diffCallback, int version, int idProduct) {
        super(diffCallback);
        this.idProduct = idProduct;
        this.version = version;
    }

    @Override
    public FormOfAccessibilityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (version == 3) {
            return FormOfAccessibilityViewHolder.create(parent, version);
        }
        return FormOfAccessibilityViewHolder.create(parent, version, idProduct);
    }

    @Override
    public void onBindViewHolder(FormOfAccessibilityViewHolder holder, int position) {
        FormOfAccessibility current = getItem(position);
        holder.bind(String.valueOf(current.getForm()), current.getIdFormOfAccessibility());
    }

    public static class FormOfAccessibilityDiff extends DiffUtil.ItemCallback<FormOfAccessibility> {

        @Override
        public boolean areItemsTheSame(@NonNull FormOfAccessibility oldItem, @NonNull FormOfAccessibility newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull FormOfAccessibility oldItem, @NonNull FormOfAccessibility newItem) {
            return oldItem.getForm().equals(newItem.getForm());
        }
    }
}
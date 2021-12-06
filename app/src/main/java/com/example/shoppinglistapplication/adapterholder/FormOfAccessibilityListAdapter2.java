package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.FormOfAccessibility;

public class FormOfAccessibilityListAdapter2 extends ListAdapter<FormOfAccessibility, FormOfAccessibilityViewHolder2> {

    private int idProduct;
    private int version;

    public FormOfAccessibilityListAdapter2(@NonNull DiffUtil.ItemCallback<FormOfAccessibility> diffCallback, int version, int idProduct) {
        super(diffCallback);
        this.idProduct = idProduct;
        this.version = version;
    }

    @Override
    public FormOfAccessibilityViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return FormOfAccessibilityViewHolder2.create(parent, version, idProduct);
    }

    @Override
    public void onBindViewHolder(FormOfAccessibilityViewHolder2 holder, int position) {
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
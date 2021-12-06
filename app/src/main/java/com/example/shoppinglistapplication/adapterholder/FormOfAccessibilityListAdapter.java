package com.example.shoppinglistapplication.adapterholder;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.shoppinglistapplication.entity.FormOfAccessibility;

public class FormOfAccessibilityListAdapter extends ListAdapter<FormOfAccessibility, FormOfAccessibilityViewHolder> {

    public FormOfAccessibilityListAdapter(@NonNull DiffUtil.ItemCallback<FormOfAccessibility> diffCallback) {
        super(diffCallback);
    }

    @Override
    public FormOfAccessibilityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FormOfAccessibilityViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(FormOfAccessibilityViewHolder holder, int position) {
        FormOfAccessibility current = getItem(position);
        holder.bind(String.valueOf(current.getForm()));
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

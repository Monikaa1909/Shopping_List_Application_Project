package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.ProductFormOfAccessibility;
import com.example.shoppinglistapplication.uiFormOfAccessibility.FormOfAccessibilityActivity;
import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductFormsActivity;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductFormOfAccessibilityViewModel;

public class FormOfAccessibilityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private int idProduct;
    private int idForm;
    private int version;

    private final TextView formItemView;

    private FormOfAccessibilityViewHolder(View itemView, int version, int idProduct) {
        super(itemView);
        formItemView = itemView.findViewById(R.id.item_name);
        itemView.setOnClickListener(this);
        this.idProduct = idProduct;
        this.version = version;
    }

    private FormOfAccessibilityViewHolder(View itemView, int version) {
        super(itemView);
        formItemView = itemView.findViewById(R.id.item_name);
        itemView.setOnClickListener(this);
        this.version = version;
    }

    public void bind(String text, int idForm) {
        formItemView.setText(text);
        this.idForm = idForm;
    }

    public static FormOfAccessibilityViewHolder create(ViewGroup parent, int version, int idProduct) {
        if (version == 2 || version == 3) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
            return new FormOfAccessibilityViewHolder(view, version, idProduct);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FormOfAccessibilityViewHolder(view, version, idProduct);
    }

    public static FormOfAccessibilityViewHolder create(ViewGroup parent, int version) {
        if (version == 2 || version == 3) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
            return new FormOfAccessibilityViewHolder(view, version);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FormOfAccessibilityViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // dodawanie formy dostępności do produktu
            ProductFormOfAccessibilityViewModel productFormOfAccessibilityViewModel = new ProductFormOfAccessibilityViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                ProductFormOfAccessibility productFormOfAccessibility = new ProductFormOfAccessibility(idProduct, idForm);
                productFormOfAccessibilityViewModel.insert(productFormOfAccessibility, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductFormsActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 2) { // usuwanie formy dostępności produktu
            ProductFormOfAccessibilityViewModel productFormOfAccessibilityViewModel = new ProductFormOfAccessibilityViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                productFormOfAccessibilityViewModel.deleteProductFormOfAccessibilityById(idProduct, idForm, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductFormsActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 3) {         // usuwanie formy
            FormOfAccessibilityViewModel formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                formOfAccessibilityViewModel.deleteFormById(idForm, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), FormOfAccessibilityActivity.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
    }
}
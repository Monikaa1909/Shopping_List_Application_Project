package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductUnitActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.uiUnitOfMeasurement.UnitsOfMeasurementActivity;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

public class UnitOfMeasurementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView unitItemView;
    private String productName;
    private int idCategory;
    private int idUnit;
    private int version;
    private int idProduct;
    private SimpleProductBuilder simpleProductBuilder;

    private UnitOfMeasurementViewHolder(View itemView, int version, SimpleProductBuilder simpleProductBuilder) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.simpleProductBuilder = simpleProductBuilder;
        unitItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private UnitOfMeasurementViewHolder(View itemView, int version, int idProduct) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.idProduct = idProduct;
        unitItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private UnitOfMeasurementViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        unitItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    public void bind(String text, int idUnit) {
        unitItemView.setText(text);
        this.idUnit = idUnit;
    }

    public static UnitOfMeasurementViewHolder create(ViewGroup parent, int version, SimpleProductBuilder simpleProductBuilder) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder(view, version, simpleProductBuilder);
    }

    public static UnitOfMeasurementViewHolder create(ViewGroup parent, int version, int idProduct) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder(view, version, idProduct);
    }

    public static UnitOfMeasurementViewHolder create(ViewGroup parent, int version) {
        if (version == 3 || version == 4) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_edit_delete, parent, false);
            return new UnitOfMeasurementViewHolder(view, version);
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // nowy produkt
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                simpleProductBuilder.setIdUnitOfMeasurement(idUnit);
//                Product product = new Product(productName, idCategory, idUnit);
                Product product = simpleProductBuilder.getResult();
                productViewModel.insert(product, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductsActivity.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 2) {  // nowy produkt dodawany w konkretnej kategorii
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                simpleProductBuilder.setIdUnitOfMeasurement(idUnit);
                Product product = simpleProductBuilder.getResult();
//                Product product = new Product(productName, idCategory, idUnit);
                productViewModel.insert(product, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);
                intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, simpleProductBuilder.getIdCategory());
                v.getContext().startActivity(intent);

                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 3) { // edytowanie jednostki produktu
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                productViewModel.updateProductUnit(idProduct, idUnit, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductUnitActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 4) { // usuwanie jednostki
            UnitOfMeasurementViewModel unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                unitOfMeasurementViewModel.deleteUnitById(idUnit, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), UnitsOfMeasurementActivity.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
    }
}

package com.example.shoppinglistapplication.viewholder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity2;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity4;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity2;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity3;
import com.example.shoppinglistapplication.uiProducts.ProductCategoryActivity;
import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView categoryItemView;
    private int idCategory;
    private int idItem;
    private int version;
    private String productName;
    private SimpleProductBuilder simpleProductBuilder;

    private CategoryViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private CategoryViewHolder(View itemView, int version, int idItem) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
        this.idItem = idItem;
    }

//    private CategoryViewHolder(View itemView, int version, String productName) {
//        super(itemView);
//        itemView.setOnClickListener(this);
//        categoryItemView = itemView.findViewById(R.id.item_name);
//        this.version = version;
//        this.productName = productName;
//    }

    private CategoryViewHolder(View itemView, int version, SimpleProductBuilder simpleProductBuilder) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
        this.simpleProductBuilder = simpleProductBuilder;
    }

    public void bind(String text, int idCategory) {
        categoryItemView.setText(text);
        this.idCategory = idCategory;
    }

    public static CategoryViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder(view, version);
    }

    public static CategoryViewHolder create(ViewGroup parent, int version, int idProduct) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder(view, version, idProduct);
    }

//    public static CategoryViewHolder create(ViewGroup parent, int version, String productName) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recyclerview_item, parent, false);
//        return new CategoryViewHolder(view, version, productName);
//    }

    public static CategoryViewHolder create(ViewGroup parent, int version, SimpleProductBuilder simpleProductBuilder) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder(view, version, simpleProductBuilder);
    }

    @Override
    public void onClick(View v) {

        if (version == 1) { // wyświetlanie produktów z danej kategorii
            Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);
            intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idCategory);
            v.getContext().startActivity(intent);

        } else if (version == 2) {  // wybór kategorii nowego składnika dania
            Intent intent = new Intent(v.getContext(), NewDishDetailActivity2.class);
            intent.putExtra(IngredientsDishActivity.KEY_CATEGORY_ID, idCategory);
            intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idItem);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();

        } else if (version == 3) {  // edytowanie kategorii produktu
            ProductViewModel productViewModel = new ProductViewModel(((Activity) v.getContext()).getApplication());
            new Thread(() -> {
                productViewModel.updateProductCategory(idItem, idCategory, emptyFunction -> {
                });
                Intent intent = new Intent(v.getContext(), ProductCategoryActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idItem);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();
            }).start();

        } else if (version == 4) {  // usuwanie kategorii
            CategoryViewModel categoryViewModel = new CategoryViewModel(((Activity) v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity) v.getContext())
                    .setView(((Activity) v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć kategorię?")
                    .setMessage(R.string.lose_your_category_data)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                categoryViewModel.deleteCategoryById(idCategory, emptyFunction -> {
                                });
                                Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity) v.getContext()).finish();
                            }).start();

                        }
                    })
                    .setNegativeButton("Anuluj usuwanie kategorii", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity) v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 5) {  // edytowanie kategorii
            Intent intent = new Intent(v.getContext(), EditCategoryActivity.class);
            intent.putExtra(EditCategoryActivity.KEY_EDIT_CATEGORY_ID, idCategory);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        } else if (version == 6) {  // wybieranie kategorii nowo dodawanego produktu
            Intent intent = new Intent(v.getContext(), NewProductActivity3.class);
            simpleProductBuilder.setIdCategory(idCategory);
            intent.putExtra("builder", simpleProductBuilder);
//            intent.putExtra(NewProductActivity.KEY_CATEGORY_ID, idCategory);
//            intent.putExtra(NewProductActivity.KEY_NEW_PRODUCT_NAME, productName);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        } else if (version == 7) {  // wybieranie kategorii nowego składnika dania
            Intent intent = new Intent(v.getContext(), NewDishActivity4.class);
            intent.putExtra(NewDishActivity2.KEY_NEW_INGREDIENT_CATEGORY_ID, idCategory);
            intent.putExtra(NewDishActivity2.KEY_NEW_DISH_ID, idItem);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }
    }
}

package com.example.shoppinglistapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiListOfPreferences.EditListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.EditPortionsActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity2;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity4;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfThePreferencesDetailActivity2;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductUnitActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.uiShoppingList.EditShoppingListActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListDetailActivity;
import com.example.shoppinglistapplication.uiUnitOfMeasurement.UnitsOfMeasurementActivity;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

class PreferencesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView preferencesItemView;
    private int version;
    private int idListOfPreferences;

    private PreferencesViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        preferencesItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    public void bind(String text, int idListOfPreferences) {
        preferencesItemView.setText(text);
        this.idListOfPreferences = idListOfPreferences;
    }

    public static PreferencesViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PreferencesViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // wyświetlanie zawartości listy
            Intent intent = new Intent(v.getContext(), CompositionListOfThePreferencesActivity.class);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
            v.getContext().startActivity(intent);
        } else if (version == 2) { // usuniecie listy
            ListOfPreferencesViewModel listOfPreferencesViewModel = new ListOfPreferencesViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć listę?")
                    .setMessage(R.string.lose_your_list_of_preferences_data)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                listOfPreferencesViewModel.deleteListOfPreferencesById(idListOfPreferences, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), ListsOfPreferencesActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();
                        }
                    })
                    .setNegativeButton("Anuluj usuwanie listy", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), ListsOfPreferencesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 3) {  // edytowanie listy
            Intent intent = new Intent(v.getContext(), EditListOfPreferencesActivity.class);
            intent.putExtra(EditListOfPreferencesActivity.KEY_EDIT_LIST_OF_PREFERENCES_ID, idListOfPreferences);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }
    }
}

class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView dishItemView;
    private int version;
    private int idDish;
    private int idItem;
    private String dishName;

    private DishViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private DishViewHolder(View itemView, int version, int idItem) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
        this.idItem = idItem;
    }

    public void bind(String text, int idDish) {
        dishItemView.setText(text);
        this.idDish = idDish;
        this.dishName = text;
    }

    public static DishViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DishViewHolder(view, version);
    }

    public static DishViewHolder create(ViewGroup parent, int version, int idItem) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DishViewHolder(view, version, idItem);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // wyświetlenie składników dania
            Intent intent = new Intent(v.getContext(), IngredientsDishActivity.class);
            intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
            v.getContext().startActivity(intent);
//            ((Activity)v.getContext()).finish();

        } else if (version == 2) {  // usuwanie dania
            DishViewModel dishViewModel = new DishViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć danie?")
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                dishViewModel.deleteDishById(idDish, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), DishesActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();
                        }
                    })
                    .setNegativeButton("Anuluj usuwanie dania", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), DishesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 3) {  // edytowanie kategorii
            Intent intent = new Intent(v.getContext(), EditDishActivity.class);
            intent.putExtra(EditDishActivity.KEY_EDIT_DISH_ID, idDish);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();

        } else if (version == 4) {  // wybór dania do listy preferencji
            Intent intent = new Intent(v.getContext(), NewListOfThePreferencesDetailActivity2.class);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idItem);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_DISH_ID, idDish);
            intent.putExtra(CompositionListOfThePreferencesActivity.KEY_DISH_NAME, dishName);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();

        } else if (version == 5) {  // wybór dania przy tworzeniu listy preferencji
            Intent intent = new Intent(v.getContext(), NewListOfPreferencesActivity4.class);
            intent.putExtra(NewListOfPreferencesActivity2.KEY_NEW_DISH_IN_LIST_OF_PREFERENCES_ID, idDish);
            intent.putExtra(NewListOfPreferencesActivity2.KEY_NEW_LIST_OF_PREFERENCES_ID, idItem);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        }
    }
}

class ShoppingListDetailViewHolder extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;

    private ShoppingListDetailViewHolder(View itemView) {
        super(itemView);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
    }

    public void bind(String name, float quantity, String unit) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(quantity) + " " + unit);
    }

    public static ShoppingListDetailViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_of_details_item, parent, false);
        return new ShoppingListDetailViewHolder(view);
    }
}

class ListOfThePreferenceDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private int version;
    private int portions;
    private int idDish;
    private int idListOfPreferences;
    private String dishName;
    private String listOfPreferencesName;

    private ListOfThePreferenceDetailViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishDetailItemView = itemView.findViewById(R.id.detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.detail_item_quantity);
        this.version = version;
    }

    public void bind(int idDish, String dishName, int idListOfPreferences, String listOfPreferencesName, int portions) {
        dishDetailItemView.setText(dishName);
        dishDetailItemView2.setText(String.valueOf(portions));

        this.idListOfPreferences = idListOfPreferences;
        this.idDish = idDish;
        this.dishName = dishName;
        this.listOfPreferencesName = listOfPreferencesName;
        this.portions = portions;
    }

    public static ListOfThePreferenceDetailViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_of_details_item, parent, false);
        return new ListOfThePreferenceDetailViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) {  // usuwanie dania z listy
            ListOfPreferencesDishViewModel listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                listOfPreferencesDishViewModel.deleteListOfPreferencesDish(idDish, idListOfPreferences, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), CompositionListOfThePreferencesActivity.class);
                intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
        else if (version == 2) {  // edytowanie składnika
            ListOfPreferencesDishViewModel listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                Intent intent = new Intent(v.getContext(), EditPortionsActivity.class);
                intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
                intent.putExtra(CompositionListOfThePreferencesActivity.KEY_DISH_ID, idDish);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
    }
}

class UnitOfMeasurementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView unitItemView;
    private String productName;
    private int idCategory;
    private int idUnit;
    private int version;
    private int idProduct;
    private SimpleProductBuilder simpleProductBuilder;

//    private UnitOfMeasurementViewHolder(View itemView, int version, String productName, int idCategory) {
//        super(itemView);
//        itemView.setOnClickListener(this);
//        this.productName = productName;
//        this.idCategory = idCategory;
//        unitItemView = itemView.findViewById(R.id.item_name);
//        this.version = version;
//    }

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

//    public static UnitOfMeasurementViewHolder create(ViewGroup parent, int version, String productName, int categoryName) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recyclerview_item, parent, false);
//        return new UnitOfMeasurementViewHolder(view, version, productName, categoryName);
//    }

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



class ShoppingListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView shoppingListItemView;
    private int version;
    private int idShoppingList;

    private ShoppingListViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        shoppingListItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    public void bind(String text, int idShoppingList) {
        shoppingListItemView.setText(text);
        this.idShoppingList = idShoppingList;
    }

    public static ShoppingListViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ShoppingListViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // wyświetlanie zawartości listy
            Intent intent = new Intent(v.getContext(), ShoppingListDetailActivity.class);
            intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID, idShoppingList);
            v.getContext().startActivity(intent);
        }
        else if (version == 2) { // usuniecie listy
            ShoppingListViewModel shoppingListViewModel = new ShoppingListViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć listę?")
                    .setMessage(R.string.lose_your_shopping_list_data)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                shoppingListViewModel.deleteShoppingListById(idShoppingList, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), ShoppingListActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();
                        }
                    })
                    .setNegativeButton("Anuluj usuwanie listy", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), ShoppingListActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 3) {  // edytowanie listy
            Intent intent = new Intent(v.getContext(), EditShoppingListActivity.class);
            intent.putExtra(EditShoppingListActivity.KEY_EDIT_SHOPPING_LIST_ID, idShoppingList);
            v.getContext().startActivity(intent);
            ((Activity) v.getContext()).finish();
        }
    }
}



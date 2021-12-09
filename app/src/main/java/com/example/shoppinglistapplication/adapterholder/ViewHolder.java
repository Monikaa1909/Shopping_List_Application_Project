package com.example.shoppinglistapplication.adapterholder;

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
import com.example.shoppinglistapplication.entity.ProductFormOfAccessibility;
import com.example.shoppinglistapplication.entity.ShoppingList;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiDishes.EditQuantityActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity2;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity4;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity5;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity2;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity3;
import com.example.shoppinglistapplication.uiFormOfAccessibility.FormOfAccessibilityActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.EditListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.EditPortionsActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity2;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity3;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfPreferencesActivity4;
import com.example.shoppinglistapplication.uiListOfPreferences.NewListOfThePreferencesDetailActivity2;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiProducts.EditProductActivity;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity3;
import com.example.shoppinglistapplication.uiProducts.ProductCategoryActivity;
import com.example.shoppinglistapplication.uiProducts.ProductDetailsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductFormsActivity;
import com.example.shoppinglistapplication.uiProducts.ProductUnitActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListActivity;
import com.example.shoppinglistapplication.uiShoppingList.ShoppingListDetailActivity;
import com.example.shoppinglistapplication.uiUnitOfMeasurement.UnitsOfMeasurementActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductFormOfAccessibilityViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

import java.util.List;

class ProductViewHolder extends RecyclerView.ViewHolder {

    private final TextView productItemView;

    private ProductViewHolder(View itemView) {
        super(itemView);
        productItemView = itemView.findViewById(R.id.item_name);
    }

    public void bind(String text) {
        productItemView.setText(text);
    }

    public static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(view);
    }
}

class ProductViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView productItemView;
    private int idProduct;
    private int idItem;
    private int version;

    private ProductViewHolder2(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        productItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private ProductViewHolder2(View itemView, int version, int idItem) {
        super(itemView);
        itemView.setOnClickListener(this);
        productItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
        this.idItem = idItem;
    }

    public void bind(String text, int idProduct) {
        productItemView.setText(text);
        this.idProduct = idProduct;
    }

    public static ProductViewHolder2 create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder2(view, version);
    }

    public static ProductViewHolder2 create(ViewGroup parent, int version, int idCategory) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder2(view, version, idCategory);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) {         // usuwanie produktu
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                productViewModel.deleteProductById(idProduct, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductsActivity.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();

        } else if (version == 2) {  // wybór produktu przy tworzeniu dania
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                List<UnitOfMeasurement> units = productViewModel.getProductUnit(idProduct);
                String unit = units.get(0).getUnit();
                Intent intent = new Intent(v.getContext(), NewDishActivity5.class);
                intent.putExtra(NewDishActivity2.KEY_NEW_DISH_ID, idItem);
                intent.putExtra(NewDishActivity2.KEY_NEW_INGREDIENT_ID, idProduct);
                intent.putExtra(NewDishActivity2.KEY_NEW_INGREDIENT_UNIT, unit);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();

        } else if (version == 3) {  // szczegóły produktu
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
            v.getContext().startActivity(intent);
//            ((Activity)v.getContext()).finish();
        } else if (version == 4) {  // edytowanie produktu
            Intent intent = new Intent(v.getContext(), EditProductActivity.class);
            intent.putExtra(EditProductActivity.KEY_EDIT_PRODUCT_ID, idProduct);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        } else if (version == 5) {         // usuwanie produktu w kategorii
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                productViewModel.deleteProductById(idProduct, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);
                intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idItem);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 6) {  // wybór produktu przy dodawaniu składnika do dania
            ProductViewModel productViewModel = new ProductViewModel(((Activity) v.getContext()).getApplication());
            new Thread(() -> {
                List<UnitOfMeasurement> units = productViewModel.getProductUnit(idProduct);
                String unit = units.get(0).getUnit();
                Intent intent = new Intent(v.getContext(), NewDishDetailActivity3.class);
                intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idItem);
                intent.putExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_ID, idProduct);
                intent.putExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_UNIT, unit);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();
            }).start();
        }
    }
}

class CategoryViewHolder3 extends RecyclerView.ViewHolder {

    private final TextView categoryItemView;

    private CategoryViewHolder3(View itemView) {
        super(itemView);
        categoryItemView = itemView.findViewById(R.id.item_name);
    }

    public void bind(String text) {
        categoryItemView.setText(text);
    }

    public static CategoryViewHolder3 create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder3(view);
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView categoryItemView;
    private int idCategory;
    private int idItem;
    private int version;
    private String productName;

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

    private CategoryViewHolder(View itemView, int version, String productName) {
        super(itemView);
        itemView.setOnClickListener(this);
        categoryItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
        this.productName = productName;
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

    public static CategoryViewHolder create(ViewGroup parent, int version, String productName) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder(view, version, productName);
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
            ((Activity)v.getContext()).finish();

        } else if (version == 3) {  // edytowanie kategorii produktu
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                productViewModel.updateProductCategory(idItem, idCategory, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductCategoryActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idItem);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();

        } else if (version == 4) {  // usuwanie kategorii
            CategoryViewModel categoryViewModel = new CategoryViewModel(((Activity)v.getContext()).getApplication());

            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                    .setTitle("Czy na pewno chcesz usunąć kategorię?")
                    .setMessage(R.string.lose_your_category_data)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new Thread(() -> {
                                categoryViewModel.deleteCategoryById(idCategory, emptyFunction -> {});
                                Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                                v.getContext().startActivity(intent);
                                ((Activity)v.getContext()).finish();
                            }).start();

                        }
                    })
                    .setNegativeButton("Anuluj usuwanie kategorii", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                            v.getContext().startActivity(intent);
                            ((Activity)v.getContext()).finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (version == 5) {  // edytowanie kategorii
            Intent intent = new Intent(v.getContext(), EditCategoryActivity.class);
            intent.putExtra(EditCategoryActivity.KEY_EDIT_CATEGORY_ID, idCategory);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        } else if (version == 6) {  // wybieranie kategorii nowo dodawanego produktu
            Intent intent = new Intent(v.getContext(), NewProductActivity3.class);
            intent.putExtra(NewProductActivity.KEY_CATEGORY_ID, idCategory);
            intent.putExtra(NewProductActivity.KEY_NEW_PRODUCT_NAME, productName);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        } else if (version == 7) {  // wybieranie kategorii nowego składnika dania
            Intent intent = new Intent(v.getContext(), NewDishActivity4.class);
            intent.putExtra(NewDishActivity2.KEY_NEW_INGREDIENT_CATEGORY_ID, idCategory);
            intent.putExtra(NewDishActivity2.KEY_NEW_DISH_ID, idItem);
            v.getContext().startActivity(intent);
            ((Activity)v.getContext()).finish();
        }
    }
}

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

class DishDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private final TextView dishDetailItemView3;
    private int version;
    private int idProduct;
    private int idDish;

    private DishDetailViewHolder(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        dishDetailItemView = itemView.findViewById(R.id.dish_detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.dish_detail_item_quantity);
        dishDetailItemView3 = itemView.findViewById(R.id.dish_detail_item_unit);
        this.version = version;
    }

    public void bind(String name, float quantity, String unit, int idProduct, int idDish) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(quantity));
        dishDetailItemView3.setText(unit);
        this.idProduct = idProduct;
        this.idDish = idDish;
    }

    public static DishDetailViewHolder create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dishes_details_item, parent, false);
        return new DishDetailViewHolder(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) {  // usuwanie składnika
            IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                ingredientsOfTheDishViewModel.deleteIngredientOfTheDishById(idProduct, idDish, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), IngredientsDishActivity.class);
                intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 2) {  // edytowanie składnika
            IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                Intent intent = new Intent(v.getContext(), EditQuantityActivity.class);
                intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
                intent.putExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_ID, idProduct);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        }
    }
}

class DishDetailViewHolder2 extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private final TextView dishDetailItemView3;

    private DishDetailViewHolder2(View itemView) {
        super(itemView);
        dishDetailItemView = itemView.findViewById(R.id.dish_detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.dish_detail_item_quantity);
        dishDetailItemView3 = itemView.findViewById(R.id.dish_detail_item_unit);
    }

    public void bind(String name, float quantity, String unit, int idProduct, int idDish) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(quantity));
        dishDetailItemView3.setText(unit);
    }

    public static DishDetailViewHolder2 create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dishes_details_item, parent, false);
        return new DishDetailViewHolder2(view);
    }
}

class ShoppingListDetailViewHolder extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;
    private final TextView dishDetailItemView3;

    private ShoppingListDetailViewHolder(View itemView) {
        super(itemView);
        dishDetailItemView = itemView.findViewById(R.id.dish_detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.dish_detail_item_quantity);
        dishDetailItemView3 = itemView.findViewById(R.id.dish_detail_item_unit);
    }

    public void bind(String name, float quantity, String unit) {
        dishDetailItemView.setText(name);
        dishDetailItemView2.setText(String.valueOf(quantity));
        dishDetailItemView3.setText(unit);
    }

    public static ShoppingListDetailViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_dishes_details_item, parent, false);
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
        dishDetailItemView = itemView.findViewById(R.id.preferences_detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.preferences_detail_item_quantity);
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
                .inflate(R.layout.recyclerview_list_of_preferences_detail_item, parent, false);
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

class ListOfThePreferenceDetailViewHolder2 extends RecyclerView.ViewHolder {

    private final TextView dishDetailItemView;
    private final TextView dishDetailItemView2;

    private ListOfThePreferenceDetailViewHolder2(View itemView) {
        super(itemView);
        dishDetailItemView = itemView.findViewById(R.id.preferences_detail_item_name);
        dishDetailItemView2 = itemView.findViewById(R.id.preferences_detail_item_quantity);
    }

    public void bind(String dishName, int portions) {
        dishDetailItemView.setText(dishName);
        dishDetailItemView2.setText(String.valueOf(portions));
    }

    public static ListOfThePreferenceDetailViewHolder2 create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_list_of_preferences_detail_item, parent, false);
        return new ListOfThePreferenceDetailViewHolder2(view);
    }
}

class UnitOfMeasurementViewHolder extends RecyclerView.ViewHolder {

    private final TextView unitItemView;

    private UnitOfMeasurementViewHolder(View itemView) {
        super(itemView);
        unitItemView = itemView.findViewById(R.id.item_name);
    }

    public void bind(String text) {
        unitItemView.setText(text);
    }

    public static UnitOfMeasurementViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder(view);
    }
}

class UnitOfMeasurementViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView unitItemView;
    private String productName;
    private int idCategory;
    private int idUnit;
    private int version;
    private int idProduct;

    private UnitOfMeasurementViewHolder2(View itemView, int version, String productName, int idCategory) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.productName = productName;
        this.idCategory = idCategory;
        unitItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private UnitOfMeasurementViewHolder2(View itemView, int version, int idProduct) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.idProduct = idProduct;
        unitItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    private UnitOfMeasurementViewHolder2(View itemView, int version) {
        super(itemView);
        itemView.setOnClickListener(this);
        unitItemView = itemView.findViewById(R.id.item_name);
        this.version = version;
    }

    public void bind(String text, int idUnit) {
        unitItemView.setText(text);
        this.idUnit = idUnit;
    }

    public static UnitOfMeasurementViewHolder2 create(ViewGroup parent, int version, String productName, int categoryName) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder2(view, version, productName, categoryName);
    }

    public static UnitOfMeasurementViewHolder2 create(ViewGroup parent, int version, int idProduct) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder2(view, version, idProduct);
    }

    public static UnitOfMeasurementViewHolder2 create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UnitOfMeasurementViewHolder2(view, version);
    }

    @Override
    public void onClick(View v) {
        if (version == 1) { // nowy produkt
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                Product product = new Product(productName, idCategory, idUnit);
                productViewModel.insert(product, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductsActivity.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }).start();
        } else if (version == 2) {  // nowy produkt dodawany w konkretnej kategorii
            ProductViewModel productViewModel = new ProductViewModel(((Activity)v.getContext()).getApplication());
            new Thread(() -> {
                Product product = new Product(productName, idCategory, idUnit);
                productViewModel.insert(product, emptyFunction -> {});
                Intent intent = new Intent(v.getContext(), ProductsByCategoryActivity.class);
                intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idCategory);
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

class FormOfAccessibilityViewHolder extends RecyclerView.ViewHolder {

    private final TextView formItemView;

    private FormOfAccessibilityViewHolder(View itemView) {
        super(itemView);
        formItemView = itemView.findViewById(R.id.item_name);
    }

    public void bind(String text) {
        formItemView.setText(text);
    }

    public static FormOfAccessibilityViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FormOfAccessibilityViewHolder(view);
    }
}

class FormOfAccessibilityViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    private int idProduct;
    private int idForm;
    private int version;

    private final TextView formItemView;

    private FormOfAccessibilityViewHolder2(View itemView, int version, int idProduct) {
        super(itemView);
        formItemView = itemView.findViewById(R.id.item_name);
        itemView.setOnClickListener(this);
        this.idProduct = idProduct;
        this.version = version;
    }

    private FormOfAccessibilityViewHolder2(View itemView, int version) {
        super(itemView);
        formItemView = itemView.findViewById(R.id.item_name);
        itemView.setOnClickListener(this);
        this.version = version;
    }

    public void bind(String text, int idForm) {
        formItemView.setText(text);
        this.idForm = idForm;
    }

    public static FormOfAccessibilityViewHolder2 create(ViewGroup parent, int version, int idProduct) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FormOfAccessibilityViewHolder2(view, version, idProduct);
    }

    public static FormOfAccessibilityViewHolder2 create(ViewGroup parent, int version) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FormOfAccessibilityViewHolder2(view, version);
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
//        else if (version == 2) { // usuniecie listy
//            ListOfPreferencesViewModel listOfPreferencesViewModel = new ListOfPreferencesViewModel(((Activity)v.getContext()).getApplication());
//
//            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)v.getContext())
//                    .setView(((Activity)v.getContext()).getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
//                    .setTitle("Czy na pewno chcesz usunąć listę?")
//                    .setMessage(R.string.lose_your_list_of_preferences_data)
//                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            new Thread(() -> {
//                                listOfPreferencesViewModel.deleteListOfPreferencesById(idListOfPreferences, emptyFunction -> {});
//                                Intent intent = new Intent(v.getContext(), ListsOfPreferencesActivity.class);
//                                v.getContext().startActivity(intent);
//                                ((Activity)v.getContext()).finish();
//                            }).start();
//                        }
//                    })
//                    .setNegativeButton("Anuluj usuwanie listy", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Intent intent = new Intent(v.getContext(), ListsOfPreferencesActivity.class);
//                            v.getContext().startActivity(intent);
//                            ((Activity)v.getContext()).finish();
//                        }
//                    });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//
//        } else if (version == 3) {  // edytowanie listy
//            Intent intent = new Intent(v.getContext(), EditListOfPreferencesActivity.class);
//            intent.putExtra(EditListOfPreferencesActivity.KEY_EDIT_LIST_OF_PREFERENCES_ID, idListOfPreferences);
//            v.getContext().startActivity(intent);
//            ((Activity) v.getContext()).finish();
//        }
    }
}



package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiProducts.EditProductActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class EditCategoryActivity extends AppCompatActivity {

    public static final String KEY_EDIT_CATEGORY_ID = "editCategoryID";
    private EditText editCategoryName;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idCategory = (int) getIntent().getSerializableExtra(KEY_EDIT_CATEGORY_ID);

        editCategoryName = findViewById(R.id.new_element_name);
        editCategoryName.setHint(R.string.hint_edit_name_category);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editCategoryName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj edytowanie kategorii", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie kategorii",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    categoryViewModel = new CategoryViewModel(this.getApplication());
                    String newCategoryName = editCategoryName.getText().toString();
                    Intent intent = new Intent(EditCategoryActivity.this, CategoriesActivity.class);
                    if (!categoryViewModel.categoryExists(newCategoryName)) {
                        categoryViewModel.updateCategoryName(idCategory, newCategoryName, emptyFunction -> {});
                    } else {
                        intent.putExtra(CategoriesActivity.KEY_CATEGORY_INFO, "editingNameExists");
                        intent.putExtra(EditCategoryActivity.KEY_EDIT_CATEGORY_ID, idCategory);
                    }
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}
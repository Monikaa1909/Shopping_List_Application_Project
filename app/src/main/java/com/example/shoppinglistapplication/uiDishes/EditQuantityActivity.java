package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;

public class EditQuantityActivity extends AppCompatActivity {

    private EditText editQuantityName;
    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idDish = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_DISH_ID);
        int idProduct = (int) getIntent().getSerializableExtra(IngredientsDishActivity.KEY_NEW_INGREDIENT_ID);

        editQuantityName = findViewById(R.id.new_element_name);
        editQuantityName.setHint(R.string.hint_edit_quantity);
        editQuantityName.setInputType(InputType.TYPE_CLASS_NUMBER);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editQuantityName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną ilość", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj edytowanie ilości", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie ilości",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());
                    int newQuantity = Integer.valueOf(editQuantityName.getText().toString());
                    ingredientsOfTheDishViewModel.updateIngredientOfTheDishQuantity(idProduct, idDish, newQuantity, emptyFunction -> {});
                    Intent intent = new Intent(EditQuantityActivity.this, IngredientsDishActivity.class);
                    intent.putExtra(IngredientsDishActivity.KEY_DISH_ID, idDish);
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}
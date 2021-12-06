package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity2;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewDishActivity5 extends AppCompatActivity {

    private EditText editQuantity;
    private TextView textUnit;
    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name_with_detail);

        int newDishId = (int) getIntent().getSerializableExtra(NewDishActivity2.KEY_NEW_DISH_ID);
        int newIngredientId = (int) getIntent().getSerializableExtra(NewDishActivity2.KEY_NEW_INGREDIENT_ID);
        String newIngredientUnit = getIntent().getStringExtra(NewDishActivity2.KEY_NEW_INGREDIENT_UNIT);

        editQuantity = findViewById(R.id.new_element_name);
        editQuantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editQuantity.setHint(R.string.hint_new_quantity);

        textUnit = findViewById(R.id.new_element_detail);
        textUnit.setText(newIngredientUnit);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editQuantity.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną ilość", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie składnika", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego składnika",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    float quantity = Float.valueOf(editQuantity.getText().toString());
                    ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());
                    if (!ingredientsOfTheDishViewModel.ingredientExists(newIngredientId, newDishId)) {
                        IngredientsOfTheDish ingredientsOfTheDish = new IngredientsOfTheDish(newIngredientId, newDishId, quantity);
                        ingredientsOfTheDishViewModel.insert(ingredientsOfTheDish, emptyFunction -> {});
                        Intent intent = new Intent(NewDishActivity5.this, NewDishActivity2.class);
                        intent.putExtra(NewDishActivity2.KEY_NEW_DISH_ID, newDishId);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewDishActivity5.this, NewDishActivity2.class);
                        Log.d("aaaa", "istnieje");
                        intent.putExtra(NewDishActivity2.KEY_NEW_DISH_ID, newDishId);
                        intent.putExtra(NewDishActivity2.KEY_DISH_INFO, "alreadyExists");
                        startActivity(intent);
                        this.finish();
                    }

                    }).start();
            }
        });
    }
}
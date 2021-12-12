package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.Dish;
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity2;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDishActivity extends AppCompatActivity {

    public static final String KEY_NEW_DISH_NAME = "newDishName";
    private EditText editDishName;
    private DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editDishName = findViewById(R.id.new_element_name);
        editDishName.setHint(R.string.hint_new_name_dish);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editDishName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie dania", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego dania",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    DataValidator validator = new DataValidator();
                    String dishName = validator.validateName(editDishName.getText().toString());
                    dishViewModel = new DishViewModel(this.getApplication());
                    if (!dishViewModel.dishExists(dishName)) {
                        Intent intent = new Intent(NewDishActivity.this, NewDishActivity2.class);
                        Dish dish = new Dish(dishName);
                        dishViewModel.insert(dish, emptyFunction -> {});
                        intent.putExtra(KEY_NEW_DISH_NAME, dishName);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewDishActivity.this, DishesActivity.class);
                        intent.putExtra(DishesActivity.KEY_DISH_INFO, "alreadyExists");
                        startActivity(intent);
                        this.finish();
                    }
                }).start();
            }
        });
    }
}
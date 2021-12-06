package com.example.shoppinglistapplication.uiDishes;

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
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;

public class EditDishActivity extends AppCompatActivity {

    public static final String KEY_EDIT_DISH_ID = "editDishID";
    private EditText editDishName;
    private DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idDish = (int) getIntent().getSerializableExtra(KEY_EDIT_DISH_ID);

        editDishName = findViewById(R.id.new_element_name);
        editDishName.setHint(R.string.hint_edit_name_dish);

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
                        .setNegativeButton("Anuluj edytowanie nazwy dania", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie nazwy dania",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    dishViewModel = new DishViewModel(this.getApplication());
                    String newDishName = editDishName.getText().toString();
                    if (!dishViewModel.dishExists(newDishName)) {
                        dishViewModel.updateDishName(idDish, newDishName, emptyFunction -> {});
                    }
                    Intent intent = new Intent(EditDishActivity.this, DishesActivity.class);
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}
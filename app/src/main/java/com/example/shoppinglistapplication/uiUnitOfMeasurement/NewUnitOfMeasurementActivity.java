package com.example.shoppinglistapplication.uiUnitOfMeasurement;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.NewCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewUnitOfMeasurementActivity extends AppCompatActivity {

    public static final String KEY_UNIT_NAME = "newUnitName";
    private EditText editUnitName;
    private UnitOfMeasurementViewModel unitOfMeasurementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editUnitName = findViewById(R.id.new_element_name);
        editUnitName.setHint(R.string.hint_new_name_unit);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editUnitName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie produktu", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej jednostki",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    String unitName = editUnitName.getText().toString();
                    unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(this.getApplication());
                    if (!unitOfMeasurementViewModel.unitExists(unitName)) {
                        Intent intent = new Intent(NewUnitOfMeasurementActivity.this, UnitsOfMeasurementActivity.class);
                        UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement(unitName);
                        unitOfMeasurementViewModel.insert(unitOfMeasurement, emptyFunction -> {});
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewUnitOfMeasurementActivity.this, UnitsOfMeasurementActivity.class);
                        intent.putExtra(UnitsOfMeasurementActivity.KEY_UNIT_INFO, "alreadyExists");
                        startActivity(intent);
                        this.finish();
                    }
                }).start();
            }
        });
    }
}
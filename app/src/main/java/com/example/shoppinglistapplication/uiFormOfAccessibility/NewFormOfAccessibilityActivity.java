package com.example.shoppinglistapplication.uiFormOfAccessibility;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import com.example.shoppinglistapplication.uiUnitOfMeasurement.NewUnitOfMeasurementActivity;
import com.example.shoppinglistapplication.uiUnitOfMeasurement.UnitsOfMeasurementActivity;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewFormOfAccessibilityActivity extends AppCompatActivity {

    private EditText editForm;
    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editForm = findViewById(R.id.new_element_name);
        editForm.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editForm.setHint(R.string.hint_new_form);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editForm.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej formy dostępności",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    float form = Float.valueOf(editForm.getText().toString());
                    formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());
                    if (!formOfAccessibilityViewModel.formExists(form)) {
                        Intent intent = new Intent(NewFormOfAccessibilityActivity.this, FormOfAccessibilityActivity.class);
                        FormOfAccessibility formOfAccessibility = new FormOfAccessibility(form);
                        formOfAccessibilityViewModel.insert(formOfAccessibility, emptyFunction -> {});
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewFormOfAccessibilityActivity.this, FormOfAccessibilityActivity.class);
                        intent.putExtra(FormOfAccessibilityActivity.KEY_INFO_FORM, "alreadyExists");
                        startActivity(intent);
                        this.finish();
                    }
                }).start();
            }
        });
    }
}
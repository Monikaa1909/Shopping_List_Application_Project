package com.example.shoppinglistapplication.uiListOfPreferences;

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
import com.example.shoppinglistapplication.uiDishes.EditQuantityActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;

public class EditPortionsActivity extends AppCompatActivity {

    private EditText editPortionsName;
    private ListOfPreferencesDishViewModel listOfPreferencesDishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idDish = (int) getIntent().getSerializableExtra(CompositionListOfThePreferencesActivity.KEY_DISH_ID);
        int idListOfPreferences = (int) getIntent().getSerializableExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID);

        editPortionsName = findViewById(R.id.new_element_name);
        editPortionsName.setHint(R.string.hint_edit_portions);
        editPortionsName.setInputType(InputType.TYPE_CLASS_NUMBER);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editPortionsName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną ilość", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj edytowanie porcji", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie porcji",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(this.getApplication());
                    int newPortions = Integer.valueOf(editPortionsName.getText().toString());
                    listOfPreferencesDishViewModel.updateListOfPreferencesDishPortions(idDish, idListOfPreferences, newPortions, emptyFunction -> {});
                    Intent intent = new Intent(EditPortionsActivity.this, CompositionListOfThePreferencesActivity.class);
                    intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, idListOfPreferences);
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}
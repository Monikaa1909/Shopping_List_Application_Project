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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity2;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity5;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;

public class NewListOfPreferencesActivity4 extends AppCompatActivity {

    private EditText editPortions;
    private ListOfPreferencesDishViewModel listOfPreferencesDishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name_with_detail);

        int newDishId = (int) getIntent().getSerializableExtra(NewListOfPreferencesActivity2.KEY_NEW_DISH_IN_LIST_OF_PREFERENCES_ID);
        int newListOfPreferencesId = (int) getIntent().getSerializableExtra(NewListOfPreferencesActivity2.KEY_NEW_LIST_OF_PREFERENCES_ID);

        editPortions = findViewById(R.id.new_element_name);
        editPortions.setInputType(InputType.TYPE_CLASS_NUMBER);
        editPortions.setHint(R.string.hint_new_portions);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editPortions.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną ilość porcji", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie dania do listy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego dania do listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    int portions = Integer.valueOf(editPortions.getText().toString());
                    listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(this.getApplication());
                    if (!listOfPreferencesDishViewModel.listOfPreferencesDishExists(newDishId, newListOfPreferencesId)) {
                        ListOfPreferencesDish listOfPreferencesDish = new ListOfPreferencesDish(newListOfPreferencesId, newDishId, portions);
                        listOfPreferencesDishViewModel.insert(listOfPreferencesDish, emptyFunction -> {});
                        Intent intent = new Intent(NewListOfPreferencesActivity4.this, CompositionListOfThePreferencesActivity.class);
                        intent.putExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID, newListOfPreferencesId);
                        startActivity(intent);
                        this.finish();
                    }
                }).start();
            }
        });
    }
}
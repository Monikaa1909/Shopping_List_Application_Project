package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.Dish;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity2;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;

public class NewListOfPreferencesActivity extends AppCompatActivity {

    public static final String KEY_NEW_LIST_OF_PREFERENCES_NAME = "newListOfPreferencesName";
    private EditText editListName;
    ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editListName = findViewById(R.id.new_element_name);
        editListName.setHint(R.string.hint_new_name_list);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editListName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowej listy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    DataValidator validator = new DataValidator();
                    String listName = validator.validateName(editListName.getText().toString());
                    listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());
                    if (!listOfPreferencesViewModel.listOfPreferencesExists(listName)) {
                        Intent intent = new Intent(NewListOfPreferencesActivity.this, NewListOfPreferencesActivity2.class);
                        ListOfPreferences listOfPreferences = new ListOfPreferences(listName);
                        listOfPreferencesViewModel.insert(listOfPreferences);
                        intent.putExtra(KEY_NEW_LIST_OF_PREFERENCES_NAME, listName);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewListOfPreferencesActivity.this,ListsOfPreferencesActivity.class);
                        intent.putExtra(ListsOfPreferencesActivity.KEY_LIST_OF_PREFERENCES_INFO, "alreadyExists");
                        startActivity(intent);
                        this.finish();
                    }
                }).start();
            }
        });
    }
}
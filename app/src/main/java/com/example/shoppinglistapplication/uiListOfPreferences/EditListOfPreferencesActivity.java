package com.example.shoppinglistapplication.uiListOfPreferences;

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
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;

public class EditListOfPreferencesActivity extends AppCompatActivity {

    public static final String KEY_EDIT_LIST_OF_PREFERENCES_ID = "editListOfPreferencesID";
    private EditText editListName;
    private ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idListOfPreferences = (int) getIntent().getSerializableExtra(KEY_EDIT_LIST_OF_PREFERENCES_ID);

        editListName = findViewById(R.id.new_element_name);
        editListName.setHint(R.string.hint_edit_name_list_of_preferences);

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
                        .setNegativeButton("Anuluj edytowanie nazwy listy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie nazwy listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());
                    DataValidator validator = new DataValidator();
                    String newListName = validator.validateName(editListName.getText().toString());
                    Intent intent = new Intent(EditListOfPreferencesActivity.this, ListsOfPreferencesActivity.class);

                    if (!listOfPreferencesViewModel.listOfPreferencesExists(newListName)) {
                        listOfPreferencesViewModel.updateListOfPreferencesName(idListOfPreferences, newListName, emptyFunction -> {});
                    } else {
                        intent.putExtra(ListsOfPreferencesActivity.KEY_LIST_OF_PREFERENCES_INFO, "editingNameExists");
                        intent.putExtra(EditListOfPreferencesActivity.KEY_EDIT_LIST_OF_PREFERENCES_ID, idListOfPreferences);
                    }
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}
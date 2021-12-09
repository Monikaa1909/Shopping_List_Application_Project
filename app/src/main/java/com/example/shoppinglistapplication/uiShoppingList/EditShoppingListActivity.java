package com.example.shoppinglistapplication.uiShoppingList;

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
import com.example.shoppinglistapplication.uiListOfPreferences.EditListOfPreferencesActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.ListsOfPreferencesActivity;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;

public class EditShoppingListActivity extends AppCompatActivity {

    public static final String KEY_EDIT_SHOPPING_LIST_ID = "editShoppingListID";
    private EditText editListName;
    private ShoppingListViewModel shoppingListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idShoppingList = (int) getIntent().getSerializableExtra(KEY_EDIT_SHOPPING_LIST_ID);

        editListName = findViewById(R.id.new_element_name);
        editListName.setHint(R.string.hint_edit_name_shopping_list);

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
                    shoppingListViewModel = new ShoppingListViewModel(this.getApplication());
                    String newListName = editListName.getText().toString();
                    Intent intent = new Intent(EditShoppingListActivity.this, ShoppingListActivity.class);

                    if (!shoppingListViewModel.shoppingListsExists(newListName)) {
                        shoppingListViewModel.updateShoppingListName(idShoppingList, newListName, emptyFunction -> {});
                    } else {
                        intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_INFO, "editingNameExists");
                        intent.putExtra(EditShoppingListActivity.KEY_EDIT_SHOPPING_LIST_ID, idShoppingList);
                    }
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}
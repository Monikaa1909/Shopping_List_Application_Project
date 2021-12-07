package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.PreferencesListAdapter;
import com.example.shoppinglistapplication.entity.ListOfPreferences;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToDeleteActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToEditActivity;
import com.example.shoppinglistapplication.uiCategories.NewCategoryActivity;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListsOfPreferencesActivity extends AppCompatActivity {

    public static final String KEY_LIST_OF_PREFERENCES_NAME = "listOfPreferencesName";
    public static final String KEY_LIST_OF_PREFERENCES_INFO = "listOfPreferencesInfo";
    private ListOfPreferencesViewModel listOfPreferencesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        if (getIntent().getStringExtra(KEY_LIST_OF_PREFERENCES_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_LIST_OF_PREFERENCES_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.list_of_preferences_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ListsOfPreferencesActivity.this, NewListOfPreferencesActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowej llisty", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (info.equals("editingNameExists")) {
                int idListOfPreferences = (int) getIntent().getSerializableExtra(EditListOfPreferencesActivity.KEY_EDIT_LIST_OF_PREFERENCES_ID);
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.list_of_preferences_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ListsOfPreferencesActivity.this, EditListOfPreferencesActivity.class);
                                intent.putExtra(EditListOfPreferencesActivity.KEY_EDIT_LIST_OF_PREFERENCES_ID, idListOfPreferences);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj edytowanie listy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PreferencesListAdapter adapter = new PreferencesListAdapter(new PreferencesListAdapter.PreferencesDiff(), 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());
        listOfPreferencesViewModel.getListOfPreferences().observe(this, preferences -> {
            adapter.submitList(preferences);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListsOfPreferencesActivity.this, NewListOfPreferencesActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(ListsOfPreferencesActivity.this, ListOfPreferencesToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(ListsOfPreferencesActivity.this, ListOfPreferencesToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }

}
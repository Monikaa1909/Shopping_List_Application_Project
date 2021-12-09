package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter;
import com.example.shoppinglistapplication.adapterholder.ListOfThePreferenceDetailAdapter;
import com.example.shoppinglistapplication.adapterholder.ListOfThePreferenceDetailAdapter2;
import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishToDeleteActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishToEditActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity;
import com.example.shoppinglistapplication.uiShoppingList.GenerateShoppingListActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CompositionListOfThePreferencesActivity extends AppCompatActivity {

    public static final String KEY_LIST_OF_THE_PREFERENCES_ID = "listOfThePreferencesID3";
    public static final String KEY_DISH_ID = "dishID3";
    public static final String KEY_DISH_NAME = "dishName3";
    public static final String KEY_PREFERENCES_INFO = "preferencesInfo";

    private ListOfPreferencesDishViewModel listOfPreferencesDishViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_composition_list_of_preferences);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Skład listy:");

        int idListOfThePreferences = (int) getIntent().getSerializableExtra(KEY_LIST_OF_THE_PREFERENCES_ID);

        if (getIntent().getStringExtra(KEY_PREFERENCES_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_PREFERENCES_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.preferences_already_exists)
                        .setPositiveButton("Wybierz inny inne danie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(CompositionListOfThePreferencesActivity.this, NewListOfThePreferencesDetailActivity.class);
                                intent.putExtra(KEY_LIST_OF_THE_PREFERENCES_ID, idListOfThePreferences);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowego dania do listy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego dania do listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        new Thread(() -> {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final ListOfThePreferenceDetailAdapter2 adapter = new ListOfThePreferenceDetailAdapter2(new ListOfThePreferenceDetailAdapter2.ListOfThePreferenceDetailDiff());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(this.getApplication());

            List<ListOfPreferencesDetail> listOfPreferencesDetails = listOfPreferencesDishViewModel.getListOfPreferencesDishDetail(idListOfThePreferences);
            adapter.submitList(listOfPreferencesDetails);

            FloatingActionButton fab = findViewById(R.id.fab_add);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(CompositionListOfThePreferencesActivity.this, NewListOfThePreferencesDetailActivity.class);
                intent.putExtra(KEY_LIST_OF_THE_PREFERENCES_ID, idListOfThePreferences);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
            deleteFab.setOnClickListener(view -> {
                Intent intent = new Intent(CompositionListOfThePreferencesActivity.this, CompositionListOfThePreferencesToDeleteActivity.class);
                intent.putExtra(KEY_LIST_OF_THE_PREFERENCES_ID, idListOfThePreferences);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton editFab = findViewById(R.id.fab_edit);
            editFab.setOnClickListener(view -> {
                Intent intent = new Intent(CompositionListOfThePreferencesActivity.this,CompositionListOfThePreferencesToEditActivity.class);
                intent.putExtra(KEY_LIST_OF_THE_PREFERENCES_ID, idListOfThePreferences);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton generateFab = findViewById(R.id.fab_generate);
            generateFab.setOnClickListener(view -> {
                Log.d("IDPREFERENCES", idListOfThePreferences + " ");
                Intent intent = new Intent(CompositionListOfThePreferencesActivity.this, GenerateShoppingListActivity.class);
                intent.putExtra(KEY_LIST_OF_THE_PREFERENCES_ID, idListOfThePreferences);
                startActivity(intent);
                this.finish();
            });

        }).start();
    }
}
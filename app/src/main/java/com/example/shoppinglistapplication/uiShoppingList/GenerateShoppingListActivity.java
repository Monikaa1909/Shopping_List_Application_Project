package com.example.shoppinglistapplication.uiShoppingList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.SettingsActivity;
import com.example.shoppinglistapplication.StartActivity;
import com.example.shoppinglistapplication.adapterholder.DishDetailAdapter2;
import com.example.shoppinglistapplication.adapterholder.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.uiDishes.DishesActivity;
import com.example.shoppinglistapplication.uiDishes.EditDishActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishToDeleteActivity;
import com.example.shoppinglistapplication.uiDishes.IngredientsDishToEditActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishActivity;
import com.example.shoppinglistapplication.uiDishes.NewDishDetailActivity;
import com.example.shoppinglistapplication.uiListOfPreferences.CompositionListOfThePreferencesActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class GenerateShoppingListActivity extends AppCompatActivity {

    private ListOfPreferencesViewModel listOfPreferencesViewModel;
    private Button button_generate_shopping_list;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_shopping_list);
        button_generate_shopping_list = findViewById(R.id.button_generate_shopping_list);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Aktualna zawartość listy:");

        int idListOfPreferences = (int) getIntent().getSerializableExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID);

        if (getIntent().getStringExtra(ShoppingListActivity.KEY_SHOPPING_LIST_INFO) != null) {
            String info = getIntent().getStringExtra(ShoppingListActivity.KEY_SHOPPING_LIST_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.shopping_list_already_exists)
                        .setPositiveButton("Podaj inną nazwę lub pozostaw pole puste", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(GenerateShoppingListActivity.this, NewShoppingListNameActivity.class);
                                intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID, idListOfPreferences);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Anuluj generowanie listy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano generowanie listy",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

//        Log.d("NAZWAPROBLEM", "nazwa juz isnieje: " + shoppingListName);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ShoppingListDetailAdapter adapter = new ShoppingListDetailAdapter(new ShoppingListDetailAdapter.ShoppingListDetailDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfPreferencesViewModel = new ListOfPreferencesViewModel(this.getApplication());

        new Thread(() -> {
            List<ShoppingListDetail> shoppingListDetails = listOfPreferencesViewModel.getShoppingListDetail(idListOfPreferences);
            adapter.submitList(shoppingListDetails);

            String listOfPreferencesName = listOfPreferencesViewModel.getNameByListOfPreferencesId(idListOfPreferences);

            button_generate_shopping_list.setOnClickListener(view -> {
                Intent intent = new Intent(GenerateShoppingListActivity.this, NewShoppingListNameActivity.class);
                intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_ID, idListOfPreferences);
                intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_PREFERENCES_NAME, listOfPreferencesName);
                startActivity(intent);
                finish();
            });

        }).start();

    }
}
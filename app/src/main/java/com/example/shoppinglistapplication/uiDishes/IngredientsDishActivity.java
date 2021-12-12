package com.example.shoppinglistapplication.uiDishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.DishDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;
import com.example.shoppinglistapplication.viewmodel.IngredientsOfTheDishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IngredientsDishActivity extends AppCompatActivity {

    public static final String KEY_DISH_ID = "dishID2";
    public static final String KEY_CATEGORY_ID = "categoryID2";
    public static final String KEY_NEW_INGREDIENT_ID = "newIngredient2";
    public static final String KEY_NEW_INGREDIENT_UNIT = "newIngredientUnit2";
    public static final String KEY_INGREDIENT_INFO = "ingredientInfo";

    private IngredientsOfTheDishViewModel ingredientsOfTheDishViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        int idDish = (int) getIntent().getSerializableExtra(KEY_DISH_ID);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Składniki:");

        if (getIntent().getStringExtra(KEY_INGREDIENT_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_INGREDIENT_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.ingredient_already_exists)
                        .setPositiveButton("Wybierz inny składnik", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(IngredientsDishActivity.this, NewDishActivity.class);
                                intent.putExtra(KEY_DISH_ID, idDish);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowego składnika", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego składnika",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        new Thread(() -> {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final DishDetailAdapter adapter = new DishDetailAdapter(new DishDetailAdapter.DishDetailDiff(), 0);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ingredientsOfTheDishViewModel = new IngredientsOfTheDishViewModel(this.getApplication());

            List<DishDetail> dishDetails = ingredientsOfTheDishViewModel.getDishDetail(idDish);
            adapter.submitList(dishDetails);

            FloatingActionButton fab = findViewById(R.id.fab_add);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(IngredientsDishActivity.this, NewDishDetailActivity.class);
                intent.putExtra(KEY_DISH_ID, idDish);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
            deleteFab.setOnClickListener(view -> {
                Intent intent = new Intent(IngredientsDishActivity.this, IngredientsDishToDeleteActivity.class);
                intent.putExtra(KEY_DISH_ID, idDish);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton editFab = findViewById(R.id.fab_edit);
            editFab.setOnClickListener(view -> {
                Intent intent = new Intent(IngredientsDishActivity.this, IngredientsDishToEditActivity.class);
                intent.putExtra(KEY_DISH_ID, idDish);
                startActivity(intent);
                this.finish();
            });
        }).start();
    }
}
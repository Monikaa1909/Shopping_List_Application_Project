package com.example.shoppinglistapplication.uiDishes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.DishListAdapter;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToDeleteActivity;
import com.example.shoppinglistapplication.uiCategories.CategoriesToEditActivity;
import com.example.shoppinglistapplication.uiCategories.NewCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.DishViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DishesActivity  extends AppCompatActivity {

    public static final String KEY_DISH_INFO = "dishInfo";
    private DishViewModel dishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        if (getIntent().getStringExtra(KEY_DISH_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_DISH_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.dish_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(DishesActivity.this, NewDishActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowego dania", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego dania",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (info.equals("editingNameExists")) {
                int idDish = (int) getIntent().getSerializableExtra(EditDishActivity.KEY_EDIT_DISH_ID);
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.dish_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(DishesActivity.this, EditDishActivity.class);
                                intent.putExtra(EditDishActivity.KEY_EDIT_DISH_ID, idDish);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj edytowanie dania", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego dania",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DishListAdapter adapter = new DishListAdapter(new DishListAdapter.DishDiff(), 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dishViewModel = new DishViewModel(this.getApplication());
        dishViewModel.getAllDishes().observe(this, dishes -> {
            adapter.submitList(dishes);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(DishesActivity.this, NewDishActivity.class);
            startActivity(intent);
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(DishesActivity.this, DishesToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(DishesActivity.this, DishesToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}

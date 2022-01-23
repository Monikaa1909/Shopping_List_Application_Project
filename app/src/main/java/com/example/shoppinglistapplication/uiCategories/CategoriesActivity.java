package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.itemState.categoryState.CategoryToShowDetailState;
import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.CategoryListAdapter;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoriesActivity extends AppCompatActivity {

    public static final String KEY_CATEGORY_INFO = "categoryInfo";
    private CategoryViewModel categoryViewModel;

    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Dostępne kategorie:");

        if (getIntent().getStringExtra(KEY_CATEGORY_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_CATEGORY_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.category_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(CategoriesActivity.this, NewCategoryActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowej kategorii", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej kategorii",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (info.equals("editingNameExists")) {
                int idCategory = (int) getIntent().getSerializableExtra(EditCategoryActivity.KEY_EDIT_CATEGORY_ID);
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.category_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(CategoriesActivity.this, EditCategoryActivity.class);
                                intent.putExtra(EditCategoryActivity.KEY_EDIT_CATEGORY_ID, idCategory);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj edytowanie kategorii", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano edytowanie kategorii",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), new CategoryToShowDetailState());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = new CategoryViewModel(this.getApplication());
        categoryViewModel.getAllCategory().observe(this, categories -> {
            adapter.submitList(categories);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, NewCategoryActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, CategoriesToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, CategoriesToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}
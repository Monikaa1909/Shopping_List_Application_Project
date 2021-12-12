package com.example.shoppinglistapplication.uiShoppingList;

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
import com.example.shoppinglistapplication.adapter.ShoppingListAdapter;
import com.example.shoppinglistapplication.viewmodel.ShoppingListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShoppingListActivity extends AppCompatActivity {

    public static final String KEY_SHOPPING_LIST_PREFERENCES_ID = "shoppingListPreferencesID";
//    public static final String KEY_SHOPPING_LIST_DETAILS = "shoppingListDetails";
    public static final String KEY_SHOPPING_LIST_PREFERENCES_NAME = "shoppingListPreferencesName";
    public static final String KEY_SHOPPING_LIST_NAME = "shoppingListName";
    public static final String KEY_SHOPPING_LIST_INFO = "shoppingListInfo";
    public static final String KEY_SHOPPING_LIST_ID = "shoppingListID";

    private ShoppingListViewModel shoppingListViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_edit_delete_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Twoje listy zakupów:");

        if (getIntent().getStringExtra(KEY_SHOPPING_LIST_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_SHOPPING_LIST_INFO);
            if (info.equals("editingNameExists")) {
                int idShoppingList = (int) getIntent().getSerializableExtra(EditShoppingListActivity.KEY_EDIT_SHOPPING_LIST_ID);
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.list_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ShoppingListActivity.this, EditShoppingListActivity.class);
                                intent.putExtra(EditShoppingListActivity.KEY_EDIT_SHOPPING_LIST_ID, idShoppingList);
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
        final ShoppingListAdapter adapter = new ShoppingListAdapter(new ShoppingListAdapter.ShoppingListDiff(), 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingListViewModel = new ShoppingListViewModel(this.getApplication());
        shoppingListViewModel.getShoppingList().observe(this, shoppingLists -> {
            adapter.submitList(shoppingLists);
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(ShoppingListActivity.this, ShoppingListActivityToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(ShoppingListActivity.this, ShoppingListActivityToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}
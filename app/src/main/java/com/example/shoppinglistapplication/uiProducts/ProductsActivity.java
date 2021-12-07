package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ProductListAdapter2;
import com.example.shoppinglistapplication.uiCategories.ProductsByCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductsActivity extends AppCompatActivity {

    public static final String KEY_PRODUCT_INFO = "productInfo";
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        if (getIntent().getStringExtra(KEY_PRODUCT_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_PRODUCT_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.product_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ProductsActivity.this, NewProductActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowego produktu", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego produktu",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        productViewModel = new ProductViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter2 adapter = new ProductListAdapter2(new ProductListAdapter2.ProductDiff(), 3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel.getAllProducts().observe(this, products -> {
            adapter.submitList(products);
        });

        FloatingActionButton addFab = findViewById(R.id.fab_add);
        addFab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, NewProductActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, ProductsToDeleteActivity.class);
            startActivity(intent);
            this.finish();
        });

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        editFab.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, ProductsToEditActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}
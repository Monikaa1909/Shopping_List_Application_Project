package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ProductListAdapter;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductsByCategoryActivity extends AppCompatActivity {

    public static final String KEY_CATEGORY_ID2 = "categoryID2";
    public static final String KEY_NEW_PRODUCT_NAME2 = "productName2";
    public static final String KEY_PRODUCT_INFO = "productInfo";
    private ProductViewModel productViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_add_delete_button);

        int idCategory = (int) getIntent().getSerializableExtra(KEY_CATEGORY_ID2);

        if (getIntent().getStringExtra(KEY_PRODUCT_INFO) != null) {
            String info = getIntent().getStringExtra(KEY_PRODUCT_INFO);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.product_already_exists)
                        .setPositiveButton("Podaj nową nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ProductsByCategoryActivity.this, NewProductInCategoryActivity.class);
                                intent.putExtra(KEY_CATEGORY_ID2, idCategory);
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

        recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(new ProductListAdapter.ProductDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());
            FloatingActionButton addFab = findViewById(R.id.fab_add);
            addFab.setOnClickListener(view -> {
                Intent intent = new Intent(ProductsByCategoryActivity.this, NewProductInCategoryActivity.class);
                intent.putExtra(KEY_CATEGORY_ID2, idCategory);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
            deleteFab.setOnClickListener(view -> {
                Intent intent = new Intent(ProductsByCategoryActivity.this, ProductsByCategoryToDeleteActivity.class);
                intent.putExtra(KEY_CATEGORY_ID2, idCategory);
                startActivity(intent);
                this.finish();
            });

            List<Product> productsList = productViewModel.getAlphabetizedProductsByCategory(idCategory);
            adapter.submitList(productsList);
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
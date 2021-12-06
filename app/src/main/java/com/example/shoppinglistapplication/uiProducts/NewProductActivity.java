package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

import java.util.List;

public class NewProductActivity extends AppCompatActivity  {

    public static final String KEY_NEW_PRODUCT_NAME = "productName";
    public static final String KEY_CATEGORY_ID = "categoryID";
//    public static final String KEY_UNIT_ID = "unitID";
//    public static final String KEY_FORM_ID = "formID";
    private ProductViewModel productViewModel;
    private EditText editProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editProductName = findViewById(R.id.new_element_name);
        editProductName.setHint(R.string.hint_new_name_product);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editProductName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie produktu", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego produktu",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    String productName = editProductName.getText().toString();
                    productViewModel = new ProductViewModel(this.getApplication());
                    if (!productViewModel.productExists(productName)) {
                        Intent intent = new Intent(NewProductActivity.this, NewProductActivity2.class);
                        intent.putExtra(KEY_NEW_PRODUCT_NAME, productName);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewProductActivity.this, ProductsActivity.class);
                        intent.putExtra(ProductsActivity.KEY_PRODUCT_INFO, "alreadyExists");
                        startActivity(intent);
                        this.finish();
                    }

                }).start();
            }
        });
    }
}
package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class NewProductInCategoryActivity extends AppCompatActivity {

    public static final String KEY_UNIT_ID2 = "unitID2";
    public static final String KEY_FORM_ID2 = "formID2";
    private EditText editProductInCategoryName;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editProductInCategoryName = findViewById(R.id.new_element_name);
        editProductInCategoryName.setHint(R.string.hint_new_name_product);

        int idCategory = (int) getIntent().getSerializableExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2);
        SimpleProductBuilder simpleProductBuilder = (SimpleProductBuilder) getIntent().getExtras().getSerializable("builder");

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editProductInCategoryName.getText())) {
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
            }else {
                new Thread(() -> {
                    DataValidator validator = new DataValidator();
                    String productName = validator.validateName(editProductInCategoryName.getText().toString());
                    productViewModel = new ProductViewModel(this.getApplication());
                    if (!productViewModel.productExists(productName)) {
                        simpleProductBuilder.setProductName(productName);
                        Intent intent = new Intent(NewProductInCategoryActivity.this, NewProductInCategoryActivity2.class);
//                        intent.putExtra(ProductsByCategoryActivity.KEY_NEW_PRODUCT_NAME2, productName);
//                        intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idCategory);
                        intent.putExtra("builder", simpleProductBuilder);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Intent intent = new Intent(NewProductInCategoryActivity.this, ProductsByCategoryActivity.class);
                        intent.putExtra(ProductsByCategoryActivity.KEY_PRODUCT_INFO, "alreadyExists");
                        intent.putExtra(ProductsByCategoryActivity.KEY_CATEGORY_ID2, idCategory);
                        startActivity(intent);
                        this.finish();
                    }

                }).start();
            }
        });
    }
}
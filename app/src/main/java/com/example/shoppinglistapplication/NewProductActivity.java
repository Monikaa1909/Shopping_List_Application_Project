package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewProductActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "newProductName";
    public static final String EXTRA_REPLY_CATEGORY = "newProductCategoryName";

    private EditText editProductName;
    private EditText editProductCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        editProductName = findViewById(R.id.edit_product_name);
        editProductCategory = findViewById(R.id.edit_product_category_name);

//        TODO dodać możliwość wyboru jednostki oraz formy dostępności; narazie domyślnie przypisywane

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editProductName.getText()) || TextUtils.isEmpty(editProductCategory.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String productName = editProductName.getText().toString();
                String categoryName = editProductCategory.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_NAME, productName);
                replyIntent.putExtra(EXTRA_REPLY_CATEGORY, categoryName);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
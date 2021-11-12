package com.example.shoppinglistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewProductActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "com.example.productlistsql.REPLY";
    public static final String EXTRA_REPLY_CATEGORY = "com.example.product2listsql.REPLY";

    private EditText editProductName;
    private EditText editProductCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        editProductName = findViewById(R.id.edit_product_name);
        editProductCategory = findViewById(R.id.edit_product_category);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editProductName.getText()) || TextUtils.isEmpty(editProductCategory.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String product_name = editProductName.getText().toString();
                String category_name = editProductName.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_NAME, product_name);
                replyIntent.putExtra(EXTRA_REPLY_CATEGORY, category_name);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
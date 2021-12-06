package com.example.shoppinglistapplication.uiCategories;

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
import com.example.shoppinglistapplication.uiProducts.NewProductActivity;
import com.example.shoppinglistapplication.uiProducts.NewProductActivity2;
import com.example.shoppinglistapplication.uiProducts.ProductsActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class NewCategoryActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "newCategoryName";
    private CategoryViewModel categoryViewModel;
    private EditText editCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editCategoryName = findViewById(R.id.new_element_name);
        editCategoryName.setHint(R.string.hint_new_name_category);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editCategoryName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawną nazwę", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie kategorii", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej kategorii",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    String category_name = editCategoryName.getText().toString();
                    categoryViewModel = new CategoryViewModel(this.getApplication());
//                    if (categoryViewModel.categoryExists(category_name)) {
//                        Intent intent = new Intent(NewCategoryActivity.this, CategoriesActivity.class);
//                        replyIntent.putExtra(CategoriesActivity.KEY_CATEGORY_INFO, "alreadyExists");
////                        setResult(RESULT_OK, replyIntent);
//                        startActivity(intent);
//                    } else {
                        replyIntent.putExtra(EXTRA_REPLY_NAME, category_name);
                        setResult(RESULT_OK, replyIntent);
//                    }
                    this.finish();

                }).start();
            }
        });
    }
}
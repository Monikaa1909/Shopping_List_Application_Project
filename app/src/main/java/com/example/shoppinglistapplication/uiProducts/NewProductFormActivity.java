package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.FormOfAccessibilityListAdapter;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;

public class NewProductFormActivity extends AppCompatActivity {

    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz formę dostępności dla produktu:");

        int idProduct = (int) getIntent().getSerializableExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter adapter = new FormOfAccessibilityListAdapter(new FormOfAccessibilityListAdapter.FormOfAccessibilityDiff(), 1, idProduct);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());

        formOfAccessibilityViewModel.getAllForms().observe(this, forms -> {
            adapter.submitList(forms);
        });
    }
}
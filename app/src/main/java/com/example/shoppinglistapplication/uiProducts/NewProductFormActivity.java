package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter2;
import com.example.shoppinglistapplication.uiFormOfAccessibility.FormOfAccessibilityActivity;
import com.example.shoppinglistapplication.uiFormOfAccessibility.NewFormOfAccessibilityActivity;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewProductFormActivity extends AppCompatActivity {

    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        int idProduct = (int) getIntent().getSerializableExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter2 adapter = new FormOfAccessibilityListAdapter2(new FormOfAccessibilityListAdapter2.FormOfAccessibilityDiff(), 1, idProduct);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());

        formOfAccessibilityViewModel.getAllUnits().observe(this, forms -> {
            adapter.submitList(forms);
        });
    }
}
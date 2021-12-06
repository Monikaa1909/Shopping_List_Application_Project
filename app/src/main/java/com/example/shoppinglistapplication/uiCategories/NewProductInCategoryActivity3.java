package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter2;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;

public class NewProductInCategoryActivity3 extends AppCompatActivity {

    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_last_step_add_product);

//        String newProductName = getIntent().getStringExtra(NewProductInCategoryActivity.KEY_NEW_PRODUCT_NAME2);
//        int newCategoryId = (int) getIntent().getSerializableExtra(NewProductInCategoryActivity.KEY_CATEGORY_ID2);
//        int newUnitId = (int) getIntent().getSerializableExtra(NewProductInCategoryActivity.KEY_UNIT_ID2);
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        final FormOfAccessibilityListAdapter2 adapter = new FormOfAccessibilityListAdapter2(new FormOfAccessibilityListAdapter2.FormOfAccessibilityDiff(), 2, newProductName, newCategoryId, newUnitId);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());
//
//        formOfAccessibilityViewModel.getAllUnits().observe(this, forms -> {
//            adapter.submitList(forms);
//        });
    }
}
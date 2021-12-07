package com.example.shoppinglistapplication.uiFormOfAccessibility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter2;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;

public class FormOfAccessibilityToDeleteActivity extends AppCompatActivity {

    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter2 adapter = new FormOfAccessibilityListAdapter2(new FormOfAccessibilityListAdapter2.FormOfAccessibilityDiff(), 3);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        formOfAccessibilityViewModel.getAllForms().observe(this, forms -> {
            adapter.submitList(forms);
        });
    }
}
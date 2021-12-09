package com.example.shoppinglistapplication.uiFormOfAccessibility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter2;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;

public class FormOfAccessibilityToDeleteActivity extends AppCompatActivity {

    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz formę dostępności, którą chcesz usunąć:");

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
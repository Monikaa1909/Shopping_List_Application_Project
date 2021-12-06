package com.example.shoppinglistapplication.uiFormOfAccessibility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;

public class FormOfAccessibilityActivity extends AppCompatActivity {

    private static final int NEW_FORM_ACTIVITY_REQUEST_CODE = 5;
    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_all_button);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter adapter = new FormOfAccessibilityListAdapter(new FormOfAccessibilityListAdapter.FormOfAccessibilityDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());

        formOfAccessibilityViewModel.getAllUnits().observe(this, forms -> {
            adapter.submitList(forms);
        });

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(FormOfAccessibilityActivity.this, NewFormOfAccessibilityActivity.class);
            startActivityForResult(intent, NEW_FORM_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_FORM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            float form = (float) data.getSerializableExtra(NewFormOfAccessibilityActivity.EXTRA_REPLY_FORM);

            new Thread(() -> {
                formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());
                if (!formOfAccessibilityViewModel.formExists(form)) {
                    FormOfAccessibility formOfAccessibility = new FormOfAccessibility(form);
                    formOfAccessibilityViewModel.insert(formOfAccessibility, emptyFunction -> {});
                }
            }).start();

        } else {

        }
    }
}
package com.example.shoppinglistapplication.uiFormOfAccessibility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.FormOfAccessibilityListAdapter;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FormOfAccessibilityActivity extends AppCompatActivity {

    public static final String KEY_INFO_FORM = "infoForm";
    private FormOfAccessibilityViewModel formOfAccessibilityViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_add_delete_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Dostępne formy dostępności:");

        if (getIntent().getStringExtra(KEY_INFO_FORM) != null) {
            String info = getIntent().getStringExtra(KEY_INFO_FORM);
            if (info.equals("alreadyExists")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawna nazwa")
                        .setMessage(R.string.form_already_exists)
                        .setPositiveButton("Podaj nową wartość", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(FormOfAccessibilityActivity.this, NewFormOfAccessibilityActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Anuluj dodawanie nowej formy dostępności", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowej formy dostępności",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter adapter = new FormOfAccessibilityListAdapter(new FormOfAccessibilityListAdapter.FormOfAccessibilityDiff(), 0);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        formOfAccessibilityViewModel = new FormOfAccessibilityViewModel(this.getApplication());

        formOfAccessibilityViewModel.getAllForms().observe(this, forms -> {
            adapter.submitList(forms);
        });

        FloatingActionButton addFab = findViewById(R.id.fab_add);
        addFab.setOnClickListener(view -> {
            Intent intent = new Intent(FormOfAccessibilityActivity.this, NewFormOfAccessibilityActivity.class);
            startActivity(intent);
            finish();
        });

        FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
        deleteFab.setOnClickListener(view -> {
            Intent intent = new Intent(FormOfAccessibilityActivity.this, FormOfAccessibilityToDeleteActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
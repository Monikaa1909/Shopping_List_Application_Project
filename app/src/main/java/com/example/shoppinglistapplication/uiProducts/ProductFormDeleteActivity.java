package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter2;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductFormDeleteActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz formy dostępności, które chcesz usunąć:");

        int idProduct = (int) getIntent().getSerializableExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter2 adapter = new FormOfAccessibilityListAdapter2(new FormOfAccessibilityListAdapter2.FormOfAccessibilityDiff(), 2, idProduct);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());
            List<FormOfAccessibility> unitsList = productViewModel.getProductForm(idProduct);
            adapter.submitList(unitsList);
        }).start();
    }
}
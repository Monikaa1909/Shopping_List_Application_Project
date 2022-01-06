package com.example.shoppinglistapplication.uiShoppingList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.OptimizedShoppingListDetailAdapter;
import com.example.shoppinglistapplication.adapter.ShoppingListCheckBoxAdapter;
import com.example.shoppinglistapplication.adapter.ShoppingListDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.OptimizedShoppingListDetail;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListCheckBox;
import com.example.shoppinglistapplication.helpfulModel.ShoppingListDetail;
import com.example.shoppinglistapplication.viewmodel.CompositionOfTheShoppingListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShoppingListCheckBoxActivity extends AppCompatActivity implements SensorEventListener {

    public static final String THEME_TYPE = "themeType";    // 0 - AppTheme, 1 - NightAppTheme
    private int themeType;

    private SensorManager sensorManager;
    private TextView subtitle;
    private Sensor sensor;

    private int idShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // -----------------------
        int sensorType = Sensor.TYPE_LIGHT;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensorType);
        themeType = (int) getIntent().getSerializableExtra(THEME_TYPE);

        if (themeType == 1) {
            setTheme(R.style.AppTheme_Dark);
        }
        else
            setTheme(R.style.Theme_ShoppingListApplication);
        // -----------------------

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_without_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Skład listy zakupów");
        idShoppingList = (int) getIntent().getSerializableExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID);
        new Thread(() -> {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            CompositionOfTheShoppingListViewModel compositionOfTheShoppingListViewModel = new CompositionOfTheShoppingListViewModel(this.getApplication());
            String typeOfShoppingList = compositionOfTheShoppingListViewModel.getTypeOfShoppingListById(idShoppingList);
            if (typeOfShoppingList.equals("simple")) {
                final ShoppingListCheckBoxAdapter adapter = new ShoppingListCheckBoxAdapter(new ShoppingListCheckBoxAdapter.ShoppingListCheckBoxDiff(), idShoppingList);
                recyclerView.setAdapter(adapter);
                List<ShoppingListCheckBox> shoppingListDetails = compositionOfTheShoppingListViewModel.getShoppingListCheckBoxByShoppingListId(idShoppingList);
                adapter.submitList(shoppingListDetails);
            } else {
                final ShoppingListCheckBoxAdapter adapter = new ShoppingListCheckBoxAdapter(new ShoppingListCheckBoxAdapter.ShoppingListCheckBoxDiff(), idShoppingList);
                recyclerView.setAdapter(adapter);
                List<ShoppingListCheckBox> shoppingListDetails = compositionOfTheShoppingListViewModel.getOptimizedShoppingListCheckBoxByShoppingListId(idShoppingList);
                adapter.submitList(shoppingListDetails);
            }
        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentValue = event.values[0];
        if (themeType == 0) {
            if (currentValue < 5000) {
                Intent intent = new Intent(this, ShoppingListDetailActivity.class);
                intent.putExtra(THEME_TYPE, 1);
                intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID, idShoppingList);
                startActivity(intent);
                onStop();
                finish();
            }
        } else {
            if (currentValue >= 5000) {
                Log.d("MNIEJSZETAG", currentValue + " ");
                Intent intent = new Intent(this, ShoppingListDetailActivity.class);
                intent.putExtra(ShoppingListActivity.KEY_SHOPPING_LIST_ID, idShoppingList);
                intent.putExtra(THEME_TYPE, 0);
                startActivity(intent);
                onStop();
                finish();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoppinglistapplication.R;

public class NewListOfPreferencesActivity extends AppCompatActivity {

    public static final String LIST_OF_PREFERENCES_NAME = "listOfPreferencesName";
    private EditText editListName;
    private Button saveName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        editListName = findViewById(R.id.new_element_name);
        editListName.setHint(R.string.hint_new_name_list);

        saveName = findViewById(R.id.button_save);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewListOfPreferencesActivity.this, OneListOfPreferencesActivity.class);
                String listOfPreferencesName;
                if (TextUtils.isEmpty(editListName.getText())) {
                    listOfPreferencesName = "Moja lista";
                } else {
                    listOfPreferencesName = editListName.getText().toString();
                }

                intent.putExtra(LIST_OF_PREFERENCES_NAME, listOfPreferencesName);
                startActivity(intent);
            }
        });
    }
}
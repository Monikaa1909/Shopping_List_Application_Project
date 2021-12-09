package com.example.shoppinglistapplication.uiListOfPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.ListOfThePreferenceDetailAdapter;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;
import com.example.shoppinglistapplication.viewmodel.ListOfPreferencesDishViewModel;

import java.util.List;

public class CompositionListOfThePreferencesToEditActivity extends AppCompatActivity {

    private ListOfPreferencesDishViewModel listOfPreferencesDishViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz danie, które chcesz edytować:");

        int idListOfThePreferences = (int) getIntent().getSerializableExtra(CompositionListOfThePreferencesActivity.KEY_LIST_OF_THE_PREFERENCES_ID);

        listOfPreferencesDishViewModel = new ListOfPreferencesDishViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ListOfThePreferenceDetailAdapter adapter = new ListOfThePreferenceDetailAdapter(new ListOfThePreferenceDetailAdapter.ListOfThePreferenceDetailDiff(), 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            List<ListOfPreferencesDetail> listOfPreferencesDetails = listOfPreferencesDishViewModel.getListOfPreferencesDishDetail(idListOfThePreferences);
            adapter.submitList(listOfPreferencesDetails);
        }).start();
    }
}
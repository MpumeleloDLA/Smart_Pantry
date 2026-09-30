
package com.example.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.adapter.PantryAdapter;
import com.example.smartpantry.database.PantryDatabase;
import com.example.smartpantry.database.recipeSuggestions;
import com.example.smartpantry.model.PantryItems;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private PantryDatabase db;
    private RecyclerView recyclerView;
    private PantryAdapter pantryAdapter;
    private Button btnAdd, btnSuggest, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = PantryDatabase.getInstance(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnSuggest = findViewById(R.id.btnSuggest);
        btnSettings = findViewById(R.id.btnSettings);

        recyclerView = findViewById(R.id.recyclerViewPantry);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            pantryAdapter = new PantryAdapter(new ArrayList<>(), db.PantryDao());
            recyclerView.setAdapter(pantryAdapter);
        }

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRecipes.class);
            startActivity(intent);
        });

        btnSuggest.setOnClickListener(v -> {
          Intent intent = new Intent(MainActivity.this, recipeSuggestions.class);
           startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetingsOfRecipes.class);
            startActivity(intent);
        });
    }
    // Add this inside MainActivity.java

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryItems(); // Refreshes the list every time MainActivity becomes visible
    }

    private void loadPantryItems() {
        new Thread(() -> {
            List<PantryItems> items = db.PantryDao().getAllItems(); // Gets all items from Room DB
            runOnUiThread(() -> {
                if (pantryAdapter != null) {
                    pantryAdapter.setItems(items); // Updates adapter list
                    pantryAdapter.notifyDataSetChanged(); // Forces RecyclerView to redraw
                }
            });
        }).start();
    }




}
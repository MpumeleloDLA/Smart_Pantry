package com.example.smartpantry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SetingsOfRecipes extends AppCompatActivity {

    private Switch swAlerts;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings_of_recipes);

        swAlerts = findViewById(R.id.swAlerts);
        sp = getSharedPreferences("app_settings", MODE_PRIVATE);

        swAlerts.setChecked(sp.getBoolean("notifications", true));

        swAlerts.setOnCheckedChangeListener((btn, isChecked) -> {
            sp.edit().putBoolean("notifications", isChecked).apply();
            Toast.makeText(this, "Preference saved", Toast.LENGTH_SHORT).show();
        });
    }
}
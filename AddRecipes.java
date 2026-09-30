package com.example.smartpantry;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantry.database.PantryDatabase;
import com.example.smartpantry.model.PantryItems;

public class AddRecipes extends AppCompatActivity {

    private EditText idName, idCategory, idQty, idUnits;
    private Button btnSave;
    private PantryDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipes);

        db = PantryDatabase.getInstance(this);

        idName = findViewById(R.id.idName);
        idCategory = findViewById(R.id.idCategory);
        idQty = findViewById(R.id.idQty);
        idUnits = findViewById(R.id.idUnits);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = idName.getText().toString().trim();
            String cat = idCategory.getText().toString().trim();
            String qtyStr = idQty.getText().toString().trim();
            String units = idUnits.getText().toString().trim();

            if (name.isEmpty() || qtyStr.isEmpty()) {
                Toast.makeText(this, "Please enter name and quantity", Toast.LENGTH_SHORT).show();
                return;
            }

            int qty = Integer.parseInt(qtyStr);

            new Thread(() -> {
                try{
                PantryItems item = new PantryItems(0, name, cat, qty, units, System.currentTimeMillis(), "");
                db.PantryDao().insertItem(item);;

                runOnUiThread(() -> {
                    Toast.makeText(this, "Saved to pantry", Toast.LENGTH_SHORT).show();
                    finish();

                });
                }
                catch(Exception e){
                    runOnUiThread(()->{
Toast.makeText(AddRecipes. this, "Saved will be returning",Toast.LENGTH_SHORT).show();
                    finish();

        });
                }

            }).start();
        });

    }
}

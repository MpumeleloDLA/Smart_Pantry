package com.example.smartpantry.database;
import androidx.room.*;
import com.example.smartpantry.model.PantryItems;
import com.example.smartpantry.model.recipe;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Insert;
import java.util.List;
import androidx.room.Dao;

@Dao
public interface PantryDao{
@Insert
    void insertItem(PantryItems item);

    @Insert
  void insertRecipe (recipe recipe);
  @Update void
    updateItem(PantryItems item);
  @Delete void
    deleteItem(PantryItems item);
   @Query("SELECT * FROM pantry_items")List <PantryItems>getAllItems();
    @Query ("SELECT * FROM pantry_items WHERE id= :id")
    PantryItems getItemById (int id );

    @Query ("SELECT * FROM recipes") List <recipe>getAllRecipes();
    @Query ("DELETE FROM recipes")void clearRecipes();


}



























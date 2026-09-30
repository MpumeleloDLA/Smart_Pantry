package com.example.smartpantry;
import com.example.smartpantry.model.recipe;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.smartpantry.R;


public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.RecipeViewHolder> {

    private List<recipe> recipeList;

    public recipeAdapter(List<recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        recipe currentRecipe = recipeList.get(position);

        holder.txtRecipeName.setText(currentRecipe.getName());

        // Convert list of required ingredients into a single formatted string
        if (currentRecipe.getReqIngredients() != null) {
            holder.txtIngredients.setText("Ingredients: " + String.join(", ", currentRecipe.getReqIngredients()));
        }

        if (currentRecipe.getSteps() != null) {
            holder.txtSteps.setText("Steps: " + String.join("\n", currentRecipe.getSteps()));
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecipe;
        TextView txtRecipeName, txtIngredients, txtSteps;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRecipe = itemView.findViewById(R.id.imgRecipe);
            txtRecipeName = itemView.findViewById(R.id.txtRecipeName);
            txtIngredients = itemView.findViewById(R.id.txtIngredients);
            txtSteps = itemView.findViewById(R.id.txtSteps);
        }
    }
}
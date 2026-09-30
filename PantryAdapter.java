


package com.example.smartpantry.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantry.R;
import com.example.smartpantry.database.PantryDao;
import com.example.smartpantry.model.PantryItems;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.ViewHolder> {

    private List<PantryItems> items;
    private PantryDao dao;

    public PantryAdapter(List<PantryItems> items, PantryDao dao) {
        this.items = items;
        this.dao = dao;
    }

    public void setItems(List<PantryItems> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PantryItems item = items.get(position);
        holder.txtName.setText(item.name);
        holder.txtQuantity.setText("Qty: " + item.quantity + " " + (item.units != null ? item.units : ""));

        holder.btnDelete.setOnClickListener(v -> {
            new Thread(() -> {
                dao.deleteItem(item);
                items.remove(position);
                // Run UI update on main thread
                holder.itemView.post(() -> notifyItemRemoved(position));
            }).start();
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtQuantity;
        Button btnDelete;

        public ViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.txtName);
            txtQuantity = v.findViewById(R.id.itemQuantity);
            btnDelete = v.findViewById(R.id.btnDelete);
        }
    }
}
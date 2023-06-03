package com.example.exstudy.ui.inventory.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exstudy.R;
import com.example.exstudy.data.model.SeedModel;

import java.util.ArrayList;
import java.util.List;

public class InventorySeedsRecyclerViewAdapter extends RecyclerView.Adapter<InventorySeedsRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<SeedModel> inventorySeeds;

    public InventorySeedsRecyclerViewAdapter(Context context, List<SeedModel> inventorySeeds) {
        this.context = context;
        this.inventorySeeds = inventorySeeds;
    }

    @NonNull
    @Override
    public InventorySeedsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.seed_item, parent, false);

        return new InventorySeedsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventorySeedsRecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in seed_item.xml
        // based on the position of the Recycler View

        SeedModel currentModel = inventorySeeds.get(position);

        holder.name.setText(currentModel.getName());
        holder.image.setImageResource(currentModel.getImage());

        String timeToGrow = "Time to grow: " + currentModel.getTimeToGrow() + "'";
        holder.time_to_grow.setText(timeToGrow);
        holder.quantity.setText(Integer.toString(currentModel.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return inventorySeeds.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the view from our seed_item.xml layout file
        // Kinda like in the onCreate method

        TextView name, time_to_grow, quantity;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.seed_item_name);
            image = itemView.findViewById(R.id.seed_item_image);
            time_to_grow = itemView.findViewById(R.id.seed_item_time_to_grow);
            quantity = itemView.findViewById(R.id.seed_item_quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(name.getText().toString(), time_to_grow.getText().toString());
                }
            });
        }
    }
}

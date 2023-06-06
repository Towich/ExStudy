package com.example.exstudy.ui.inventory.fruits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exstudy.R;
import com.example.exstudy.data.model.FruitModel;

import java.util.List;

public class InventoryFruitsRecyclerViewAdapter extends RecyclerView.Adapter<InventoryFruitsRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<FruitModel> inventory_fruits;

    public InventoryFruitsRecyclerViewAdapter(Context context, List<FruitModel> inventory_fruits) {
        this.context = context;
        this.inventory_fruits = inventory_fruits;
    }

    @NonNull
    @Override
    public InventoryFruitsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.seed_item, parent, false);

        return new InventoryFruitsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryFruitsRecyclerViewAdapter.MyViewHolder holder, int position) {
        FruitModel fruitModel = inventory_fruits.get(position);

        holder.name.setText(fruitModel.getName());
        holder.image.setImageResource(fruitModel.getImage());
        holder.def_price.setText(Integer.toString(fruitModel.getDefPrice()));
        holder.quantity.setText(Integer.toString(fruitModel.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return inventory_fruits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, def_price, quantity;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.seed_item_name);
            image = itemView.findViewById(R.id.seed_item_image);
            def_price = itemView.findViewById(R.id.seed_item_time_to_grow);
            quantity = itemView.findViewById(R.id.seed_item_quantity);
        }
    }
}

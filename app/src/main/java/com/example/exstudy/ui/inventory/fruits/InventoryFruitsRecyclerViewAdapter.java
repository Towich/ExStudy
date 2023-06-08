package com.example.exstudy.ui.inventory.fruits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exstudy.R;
import com.example.exstudy.data.model.FruitModel;

import java.util.List;

public class InventoryFruitsRecyclerViewAdapter extends RecyclerView.Adapter<InventoryFruitsRecyclerViewAdapter.MyViewHolder> {

    private SelectListener listener;
    private Context context;
    private List<FruitModel> inventory_fruits;

    public InventoryFruitsRecyclerViewAdapter(Context context, List<FruitModel> inventory_fruits, SelectListener listener) {
        this.context = context;
        this.inventory_fruits = inventory_fruits;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InventoryFruitsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fruit_item, parent, false);

        return new InventoryFruitsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryFruitsRecyclerViewAdapter.MyViewHolder holder, int position) {
        FruitModel fruitModel = inventory_fruits.get(position);

        holder.name.setText(fruitModel.getName());
        holder.image.setImageResource(fruitModel.getImage());
        holder.button_sell.setText("Sell: " + fruitModel.getDefPrice() + '$');
        holder.button_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(fruitModel);
            }
        });
        holder.quantity.setText(Integer.toString(fruitModel.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return inventory_fruits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button button_sell;
        TextView name, quantity;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.fruit_item_name);
            image = itemView.findViewById(R.id.fruit_item_image);
            button_sell = itemView.findViewById(R.id.fruit_item_button_sell);
            quantity = itemView.findViewById(R.id.fruit_item_quantity);
        }
    }
}

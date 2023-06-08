package com.example.exstudy.ui.inventory.fruits;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exstudy.R;
import com.example.exstudy.data.model.FruitModel;
import com.example.exstudy.databinding.FragmentInventoryFruitsBinding;

public class InventoryFruitsFragment extends Fragment implements SelectListener {

    FragmentInventoryFruitsBinding binding;
    private InventoryFruitsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInventoryFruitsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(InventoryFruitsViewModel.class);

        RecyclerView recyclerView = binding.inventoryFruitsRecyclerView;

        mViewModel.getInventory_fruits().observe(getViewLifecycleOwner(), fruitModels -> {
            InventoryFruitsRecyclerViewAdapter adapter = new InventoryFruitsRecyclerViewAdapter(getContext(), mViewModel.getListMyFruits(), this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }

    @Override
    public void onItemClicked(FruitModel fruitModel) {
        mViewModel.increaseMoney(fruitModel.getDefPrice());
        mViewModel.changeQuantityOfFruit(fruitModel, -1);
    }
}
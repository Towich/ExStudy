package com.example.exstudy.ui.inventory.seeds;

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

import com.example.exstudy.databinding.FragmentInventoryBinding;
import com.example.exstudy.ui.inventory.seeds.adapter.InventorySeedsRecyclerViewAdapter;

public class InventorySeedsFragment extends Fragment {

    FragmentInventoryBinding binding;
    private InventorySeedsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInventoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(InventorySeedsViewModel.class);

        //mViewModel.insertSeed(new SeedEntity("Lemon", R.drawable.seeds_lemon, 30, 2));

        RecyclerView recyclerView = binding.inventoryRecyclerView;

        // Update InventorySeeds
        mViewModel.getInventorySeeds().observe(getViewLifecycleOwner(), inventorySeeds -> {
            InventorySeedsRecyclerViewAdapter adapter = new InventorySeedsRecyclerViewAdapter(getContext(), mViewModel.getSeeds());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}
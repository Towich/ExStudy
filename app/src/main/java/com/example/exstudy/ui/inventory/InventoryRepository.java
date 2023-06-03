package com.example.exstudy.ui.inventory;

import androidx.lifecycle.MutableLiveData;

import com.example.exstudy.data.model.SeedModel;

import java.util.List;

public class InventoryRepository {

    private MutableLiveData<List<SeedModel>> inventory_seeds;

    public InventoryRepository(){
        inventory_seeds = InventoryDataSource.createInventory();
    }

    public MutableLiveData<List<SeedModel>> getInventorySeeds() {
        return inventory_seeds;
    }
}

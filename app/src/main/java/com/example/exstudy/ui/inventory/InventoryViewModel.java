package com.example.exstudy.ui.inventory;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.exstudy.data.model.SeedModel;

import java.util.List;

public class InventoryViewModel extends ViewModel {

    private MutableLiveData<List<SeedModel>> inventory_seeds;
    private InventoryRepository mRepository;

    public InventoryViewModel(){
        mRepository = new InventoryRepository();
        inventory_seeds = mRepository.getInventorySeeds();
    }

    public List<SeedModel> getInventorySeeds() {
        return inventory_seeds.getValue();
    }
}
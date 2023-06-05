package com.example.exstudy.ui.inventory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.exstudy.data.model.SeedModel;
import com.example.exstudy.data.room.SeedEntity;

import java.util.List;

public class InventorySeedsViewModel extends AndroidViewModel {

    private LiveData<List<SeedModel>> inventory_seeds;
    private InventorySeedsRepository mRepository;

    public InventorySeedsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new InventorySeedsRepository(application);
        inventory_seeds = mRepository.getInventorySeeds();
    }

    public void insertSeed(SeedEntity seedEntity){
        mRepository.insertSeed(seedEntity);
    }

    public LiveData<List<SeedModel>> getInventorySeeds() {
        return inventory_seeds;
    }
    public List<SeedModel> getSeeds(){
        return inventory_seeds.getValue();
    }
}
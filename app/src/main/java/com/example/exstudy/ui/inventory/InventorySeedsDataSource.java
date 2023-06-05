package com.example.exstudy.ui.inventory;

import androidx.lifecycle.MutableLiveData;

import com.example.exstudy.R;
import com.example.exstudy.data.model.SeedModel;

import java.util.ArrayList;
import java.util.List;

public class InventorySeedsDataSource {

    public static final String KEY_SEEDS_NAME = "KEY_SEEDS_NAME";
    public static final String KEY_SEEDS_TIME_TO_GROW = "KEY_SEEDS_TIME_TO_GROW";
    public static final String KEY_SEEDS_IMAGE = "KEY_SEEDS_IMAGE";

    private static MutableLiveData<List<SeedModel>> inventory_seeds;

    public static MutableLiveData<List<SeedModel>> createInventory(){
        inventory_seeds = new MutableLiveData<>();

        List<SeedModel> seedModels = new ArrayList<>();

        SeedModel lemonModel = new SeedModel("Lemon", R.drawable.seeds_lemon, 1, 1);
        SeedModel coconutModel = new SeedModel("Coconut", R.drawable.seeds_coconut, 45, 2);
        seedModels.add(lemonModel);
        seedModels.add(coconutModel);
        seedModels.add(coconutModel);
        seedModels.add(lemonModel);
        seedModels.add(lemonModel);
        seedModels.add(coconutModel);
        seedModels.add(lemonModel);
        seedModels.add(lemonModel);
        seedModels.add(coconutModel);
        seedModels.add(lemonModel);
        seedModels.add(lemonModel);
        seedModels.add(coconutModel);
        seedModels.add(lemonModel);

        inventory_seeds.setValue(seedModels);
        return inventory_seeds;
    }

    public static void addSeed(){

    }
}

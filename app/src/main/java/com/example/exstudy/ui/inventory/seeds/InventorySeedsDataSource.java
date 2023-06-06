package com.example.exstudy.ui.inventory.seeds;

import androidx.lifecycle.MutableLiveData;

import com.example.exstudy.R;
import com.example.exstudy.data.model.FruitModel;
import com.example.exstudy.data.model.SeedModel;

import java.util.ArrayList;
import java.util.List;

public class InventorySeedsDataSource {

    public static final String KEY_SEEDS_NAME = "KEY_SEEDS_NAME";
    public static final String KEY_SEEDS_TIME_TO_GROW = "KEY_SEEDS_TIME_TO_GROW";
    public static final String KEY_SEEDS_IMAGE = "KEY_SEEDS_IMAGE";

    private static MutableLiveData<List<SeedModel>> inventory_seeds;

    private static final SeedModel[] all_seeds = {
            new SeedModel("Lemon", R.drawable.seeds_lemon, 1, 1),
            new SeedModel("Coconut", R.drawable.seeds_coconut, 45, 1)
    };

    private static final FruitModel[] all_fruits = {
            new FruitModel("Lemon", R.drawable.lemon, 10, 1),
            new FruitModel("Coconut", R.drawable.coconut_cut, 15, 1)
    };

    public static FruitModel getFruitByName(String fruitName){
        for (FruitModel f:all_fruits) {
            if(f.getName().equals(fruitName)){
                return f;
            }
        }
        return null;
    }
}

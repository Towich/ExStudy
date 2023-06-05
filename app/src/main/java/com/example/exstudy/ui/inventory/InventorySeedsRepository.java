package com.example.exstudy.ui.inventory;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.exstudy.R;
import com.example.exstudy.data.model.SeedModel;
import com.example.exstudy.data.room.SeedEntity;
import com.example.exstudy.data.room.SeedFruitDao;
import com.example.exstudy.data.room.SeedFruitDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventorySeedsRepository {

    private SeedFruitDao mDao;
    private LiveData<List<SeedModel>> inventory_seeds;

    public InventorySeedsRepository(Application application){
        //inventory_seeds = InventorySeedsDataSource.createInventory();

        SeedFruitDatabase db = SeedFruitDatabase.getDatabase(application);
        mDao = db.seedFruitDao();


        // Transform List of Entities to List of Models
        inventory_seeds = Transformations.map(mDao.getAllMySeeds(), entities -> entities.stream()
                .map(SeedEntity::toModel).collect(Collectors.toList()));

        insertSeed(new SeedEntity("Lemon", R.drawable.seeds_lemon, 30, 1));
        insertSeed(new SeedEntity("Coconut", R.drawable.seeds_coconut, 45, 5));
    }

    public LiveData<List<SeedModel>> getInventorySeeds() {
        return inventory_seeds;
    }

    public void insertSeed(SeedEntity seedEntity){
        SeedFruitDatabase.databaseWriteExecutor.execute(() -> {
            mDao.insertSeed(seedEntity);
        });
    }
}

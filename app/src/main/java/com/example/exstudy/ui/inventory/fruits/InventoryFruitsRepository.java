package com.example.exstudy.ui.inventory.fruits;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.exstudy.data.model.FruitModel;
import com.example.exstudy.data.room.FruitEntity;
import com.example.exstudy.data.room.SeedFruitDao;
import com.example.exstudy.data.room.SeedFruitDatabase;
import com.example.exstudy.ui.home.HomeDataSource;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryFruitsRepository {

    private LiveData<List<FruitModel>> inventory_fruits;
    private SeedFruitDao dao;

    public InventoryFruitsRepository(Application application) {
        SeedFruitDatabase db = SeedFruitDatabase.getDatabase(application);
        dao = db.seedFruitDao();

        inventory_fruits = Transformations.map(dao.getAllMyFruits(), entities -> entities.stream()
                .map(FruitEntity::toModel).collect(Collectors.toList()));
    }

    public LiveData<List<FruitModel>> getInventory_fruits() {
        return inventory_fruits;
    }

    public void insertFruit(FruitModel fruitEntity){
        SeedFruitDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertFruit(fruitEntity.toEntity());
        });
    }

    public void increaseMoney(int delta){
        HomeDataSource.increaseMoney(delta);
    }

    public void changeQuantityOfFruit(FruitModel fruitModel, int delta){
        SeedFruitDatabase.databaseWriteExecutor.execute(() -> {
            dao.increaseFruitQuantity(fruitModel.getName(), delta);
            fruitModel.setQuantity(fruitModel.getQuantity() + delta);

            dao.removeEmptyFruits();
        });
    }
}

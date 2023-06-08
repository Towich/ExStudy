package com.example.exstudy.ui.inventory.fruits;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.exstudy.data.model.FruitModel;
import com.example.exstudy.data.room.FruitEntity;

import java.util.List;

public class InventoryFruitsViewModel extends AndroidViewModel {

    private LiveData<List<FruitModel>> inventory_fruits;

    private InventoryFruitsRepository mRepository;

    public InventoryFruitsViewModel(@NonNull Application application) {
        super(application);

        mRepository = new InventoryFruitsRepository(application);
        inventory_fruits = mRepository.getInventory_fruits();
    }

    public LiveData<List<FruitModel>> getInventory_fruits() {
        return inventory_fruits;
    }

    public List<FruitModel> getListMyFruits(){
        return inventory_fruits.getValue();
    }

    public void insertFruit(FruitModel fruitEntity){
        mRepository.insertFruit(fruitEntity);
    }

    public void increaseMoney(int delta){
        mRepository.increaseMoney(delta);
    }

    public void changeQuantityOfFruit(FruitModel fruitModel, int delta){
        mRepository.changeQuantityOfFruit(fruitModel, delta);
    }
}
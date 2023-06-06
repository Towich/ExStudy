package com.example.exstudy.ui.home;

import android.app.Application;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.exstudy.data.model.FruitModel;
import com.example.exstudy.data.room.FruitEntity;
import com.example.exstudy.data.room.SeedFruitDao;
import com.example.exstudy.data.room.SeedFruitDatabase;
import com.example.exstudy.ui.inventory.seeds.InventorySeedsDataSource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HomeRepository {
    private final MutableLiveData<String> mTimerText;
    private final MutableLiveData<String> mTimerTextSeconds;
    private final MutableLiveData<Boolean> mShowingPlantResult;
    private CountDownTimer timer;

    private SeedFruitDao mDao;

    public HomeRepository(Application application){
        mTimerText = HomeDataSource.createTimerTextData();
        mTimerTextSeconds = HomeDataSource.createTimerTextSecondsData();
        mShowingPlantResult = HomeDataSource.createShowingPlantResult();

        SeedFruitDatabase db = SeedFruitDatabase.getDatabase(application);
        mDao = db.seedFruitDao();
    }

    public MutableLiveData<String> getTimerText() {
        return mTimerText;
    }

    public MutableLiveData<String> getTimerTextSeconds() {
        return mTimerTextSeconds;
    }

    public void startTimer(int duration){
        timer = HomeDataSource.createTimer(duration).start();
    }

    public void stopTimer(String seeds_minutes){
        mTimerText.setValue(seeds_minutes);
        mTimerTextSeconds.setValue(HomeDataSource.DEFAULT_SECONDS);
        timer.cancel();
    }

    public MutableLiveData<Boolean> getShowingPlantResult(){
        return mShowingPlantResult;
    }

    public void getFruitInDatabase(String seedsName){
        SeedFruitDatabase.databaseWriteExecutor.execute(() -> {
            FruitModel fruitModel = InventorySeedsDataSource.getFruitByName(seedsName);


//            FruitEntity fruitEntity = mDao.getFruitByName(seedsName).getValue();
//            if(fruitEntity == null)
//                mDao.insertFruit(fruitModel.toEntity());
        });
    }

    public void collectFruit(String nameChosenSeeds, boolean fruitInInventory){
        SeedFruitDatabase.databaseWriteExecutor.execute(() -> {
            FruitModel fruitModel = InventorySeedsDataSource.getFruitByName(nameChosenSeeds);

            Future future = SeedFruitDatabase.databaseWriteExecutor.submit(new Callable() {
                public Object call() throws Exception{
                    return mDao.getFruitByName(nameChosenSeeds);
                }
            });

            FruitEntity fruitEntity;
            try {
                fruitEntity = (FruitEntity) future.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            Log.d("collectFruit()", fruitModel.getName());
            if(fruitEntity != null){
                mDao.increaseFruitQuantity(nameChosenSeeds, 1);
            }
            else{
                mDao.insertFruit(fruitModel.toEntity());
            }
        });
    }
}

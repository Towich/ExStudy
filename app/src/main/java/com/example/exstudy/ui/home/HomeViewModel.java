package com.example.exstudy.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.exstudy.data.model.FruitModel;

public class HomeViewModel extends AndroidViewModel {

    private final HomeRepository mHomeRepository;

    private final MutableLiveData<String> mTimerText;

    private final MutableLiveData<String> mTimerTextSeconds;

    private final MutableLiveData<Boolean> mShowingPlantResult;
    private boolean timerEnabled;
    private boolean readyToCollect;
    private String nameChosenSeeds;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mHomeRepository = new HomeRepository(application);

        mTimerText = mHomeRepository.getTimerText();
        mTimerTextSeconds = mHomeRepository.getTimerTextSeconds();
        mShowingPlantResult = mHomeRepository.getShowingPlantResult();

        timerEnabled = false;
        readyToCollect = false;
    }

    public LiveData<String> getText() {
        return mTimerText;
    }

    public void setTimerMinutes(String timerMinutes){
        mTimerText.setValue(timerMinutes);
    }

    public String getTimerString() {
        return mHomeRepository.getTimerText().getValue();
    }

    public void startTimer(int duration){
        mHomeRepository.startTimer(duration);
        timerEnabled = true;
    }

    public void stopTimer(String seeds_minutes){
        mHomeRepository.stopTimer(seeds_minutes);
        timerEnabled = false;
    }

    public MutableLiveData<String> getTimerTextSeconds() {
        return mTimerTextSeconds;
    }

    public void setTimerSeconds(String timerSeconds){
        mTimerTextSeconds.setValue(timerSeconds);
    }
    public String getTimerSecondsString(){
        return mTimerTextSeconds.getValue();
    }

    public boolean isTimerEnabled() {
        return timerEnabled;
    }

    public void setTimerEnabled(boolean timerEnabled) {
        this.timerEnabled = timerEnabled;
    }

    public MutableLiveData<Boolean> getShowingPlantResult(){
        return mShowingPlantResult;
    }

    public boolean isShowingPlantResult(){
        return mShowingPlantResult.getValue();
    }

    public boolean isReadyToCollect() {
        return readyToCollect;
    }

    public void setReadyToCollect(boolean readyToCollect) {
        this.readyToCollect = readyToCollect;
    }

    public String getNameOfChosenSeeds() {
        return nameChosenSeeds;
    }

    public void setNameChosenSeeds(String seeds_name) {
        this.nameChosenSeeds = seeds_name;
    }

    public void getCurrentFruitInDatabase(){
        mHomeRepository.getFruitInDatabase(nameChosenSeeds);
    }

    public void collectPlant(){
        mHomeRepository.collectFruit(nameChosenSeeds, true);
    }
}

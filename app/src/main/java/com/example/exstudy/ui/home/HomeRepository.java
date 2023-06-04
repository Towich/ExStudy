package com.example.exstudy.ui.home;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomeRepository {
    private final MutableLiveData<String> mTimerText;
    private final MutableLiveData<String> mTimerTextSeconds;
    private final MutableLiveData<Boolean> mShowingPlantResult;
    private CountDownTimer timer;

    public HomeRepository(){
        mTimerText = HomeDataSource.createTimerTextData();
        mTimerTextSeconds = HomeDataSource.createTimerTextSecondsData();
        mShowingPlantResult = HomeDataSource.createShowingPlantResult();
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
}

package com.example.exstudy.ui.home;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomeRepository {
    private final MutableLiveData<String> mTimerText;
    private final MutableLiveData<String> mTimerTextSeconds;
    private CountDownTimer timer;
    public HomeRepository(){
        mTimerText = HomeDataSource.createTimerTextData();
        mTimerTextSeconds = HomeDataSource.createTimerTextSecondsData();
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

    public void stopTimer(){
        timer.cancel();
    }
}

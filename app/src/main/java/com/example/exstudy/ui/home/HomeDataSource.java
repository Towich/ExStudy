package com.example.exstudy.ui.home;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomeDataSource {

    private static MutableLiveData<String> timerText;
    private static MutableLiveData<String> timerTextSeconds;

    public static MutableLiveData<String> createTimerTextData(){
        timerText = new MutableLiveData<>();
        timerText.setValue("30'");
        return timerText;
    }

    public static MutableLiveData<String> createTimerTextSecondsData(){
        timerTextSeconds = new MutableLiveData<>();
        timerTextSeconds.setValue("00''");
        return timerTextSeconds;
    }

    public static CountDownTimer createTimer(int duration){
        return new CountDownTimer(duration * 1000L + 500, 1000) {

            final NumberFormat f = new DecimalFormat("00");
            // Every tick
            public void onTick(long millisUntilFinished) {
                long sec = (millisUntilFinished / 1000) % 60;
                long min = millisUntilFinished / 60000;
                timerText.setValue(f.format(min) + "'");
                timerTextSeconds.setValue(f.format(sec) + "''");
                Log.i("TIMER", f.format(min) + ':' + f.format(sec));
            }
            // When the task is over
            public void onFinish() {
                timerText.setValue("это победа");
                timerTextSeconds.setValue("");
            }
        };
    }
}

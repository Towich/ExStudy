package com.example.exstudy.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomeDataSource {

    public static final String DEFAULT_MINUTES = "30";
    public static final String DEFAULT_SECONDS = "00";
    public static final String DEFAULT_SEEDS = "Lemon";

    public static final String KEY_MONEY = "KEY_MONEY";

    private static final String SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static MutableLiveData<String> timerText;
    private static MutableLiveData<String> timerTextSeconds;

    private static MutableLiveData<Boolean> showingPlantResult;

    public static MutableLiveData<String> createTimerTextData(){
        timerText = new MutableLiveData<>();
        timerText.setValue(DEFAULT_MINUTES);
        return timerText;
    }

    public static MutableLiveData<String> createTimerTextSecondsData(){
        timerTextSeconds = new MutableLiveData<>();
        timerTextSeconds.setValue(DEFAULT_SECONDS);
        return timerTextSeconds;
    }

    public static MutableLiveData<Boolean> createShowingPlantResult(){
        showingPlantResult = new MutableLiveData<>();
        //showingPlantResult.setValue(false);
        return showingPlantResult;
    }

    public static CountDownTimer createTimer(int duration){
        return new CountDownTimer(duration * 1000L + 500, 1000) {

            final NumberFormat f = new DecimalFormat("00");
            // Every tick
            public void onTick(long millisUntilFinished) {
                long sec = (millisUntilFinished / 1000) % 60;
                long min = millisUntilFinished / 60000;
                timerText.setValue(f.format(min));
                timerTextSeconds.setValue(f.format(sec));
                Log.i("TIMER", f.format(min) + ':' + f.format(sec));
            }
            // When the task is over
            public void onFinish() {
                timerText.setValue("00");
                timerTextSeconds.setValue("00");
                showingPlantResult.setValue(true);
            }
        };
    }

    public static void initSharedPreferences(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void save(int moneyToSave){
        editor.putInt(KEY_MONEY, moneyToSave);
        editor.apply();
    }

    public static void increaseMoney(int delta){
        editor.putInt(KEY_MONEY, loadMoney() + delta);
        editor.apply();
    }

    public static int loadMoney(){
        return sharedPreferences.getInt(KEY_MONEY, -1);
    }
}

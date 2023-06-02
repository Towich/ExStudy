package com.example.exstudy.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final HomeRepository mHomeRepository;

    private final MutableLiveData<String> mTimerText;

    private final MutableLiveData<String> mTimerTextSeconds;

    private boolean timerEnabled;

    public HomeViewModel() {
        mHomeRepository = new HomeRepository();

        mTimerText = mHomeRepository.getTimerText();
        mTimerTextSeconds = mHomeRepository.getTimerTextSeconds();
        timerEnabled = false;
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

    public void stopTimer(){
        mHomeRepository.stopTimer();
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
}
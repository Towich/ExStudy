package com.example.exstudy.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final HomeRepository mHomeRepository;

    private final MutableLiveData<String> mTimerText;

    private final MutableLiveData<String> mTimerTextSeconds;

    public HomeViewModel() {
        mHomeRepository = new HomeRepository();
        mTimerText = mHomeRepository.getTimerText();
        mTimerTextSeconds = mHomeRepository.getTimerTextSeconds();
    }

    public LiveData<String> getText() {
        return mTimerText;
    }

    public String getTimerString() {
        return mHomeRepository.getTimerText().getValue();
    }

    public void startTimer(int duration){
        mHomeRepository.startTimer(duration);
    }

    public void stopTimer(){ mHomeRepository.stopTimer(); }

    public MutableLiveData<String> getTimerTextSeconds() {
        return mTimerTextSeconds;
    }

    public String getTimerSecondsString(){
        return mTimerTextSeconds.getValue();
    }
}
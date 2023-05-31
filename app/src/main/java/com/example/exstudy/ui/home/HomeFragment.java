package com.example.exstudy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.exstudy.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewTimer = binding.textHome;
        final TextView textViewTimerSeconds = binding.textHomeTimerSeconds;
        final Button buttonStartTimer = binding.buttonHomeStartTimer;

        homeViewModel.getText().observe(getViewLifecycleOwner(), mTimerText -> {
            textViewTimer.setText(homeViewModel.getTimerString());
        });

        homeViewModel.getTimerTextSeconds().observe(getViewLifecycleOwner(), mTimerTextSeconds -> {
            textViewTimerSeconds.setText(homeViewModel.getTimerSecondsString());
        });

        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.startTimer(10);
                buttonStartTimer.setText("STOP");
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeViewModel.stopTimer();
        binding = null;
    }
}
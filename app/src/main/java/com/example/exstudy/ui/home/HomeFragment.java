package com.example.exstudy.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.exstudy.R;
import com.example.exstudy.databinding.FragmentHomeBinding;
import com.example.exstudy.ui.settings.SettingsFragment;

public class HomeFragment extends Fragment {

    HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewTimer = binding.textHome;
        final TextView textViewTimerSeconds = binding.textHomeTimerSeconds;

        textViewTimerSeconds.setBackgroundColor(0);
        final ImageButton buttonStartTimer = binding.buttonHomeStartTimer;
        final ImageButton buttonSettings = binding.buttonHomeSettings;
        final ProgressBar progressBar = binding.progressBarHome;

        homeViewModel.getText().observe(getViewLifecycleOwner(), mTimerText -> {
            String current_minutes = homeViewModel.getTimerString() + "'";
            textViewTimer.setText(current_minutes);
        });

        homeViewModel.getTimerTextSeconds().observe(getViewLifecycleOwner(), mTimerTextSeconds -> {
            String current_seconds = homeViewModel.getTimerSecondsString();

            int current_minutes_int = Integer.parseInt(homeViewModel.getTimerString());
            int current_seconds_int = Integer.parseInt(current_seconds);

            progressBar.setProgress(current_minutes_int * 60 + current_seconds_int);

            current_seconds += "''";
            textViewTimerSeconds.setText(current_seconds);
        });

        getParentFragmentManager().setFragmentResultListener(SettingsFragment.RESULT_SETTINGS_FRAGMENT,
                this, (requestKey, result) -> {
                    Log.i("HOMEFRAGMENT", result.toString());

                    String minutes = result.getString(SettingsFragment.MINUTES_KEY);
                    String seconds = result.getString(SettingsFragment.SECONDS_KEY);

                    homeViewModel.setTimerMinutes(minutes);
                    homeViewModel.setTimerSeconds(seconds);

                    progressBar.setMax(Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds));
                });

        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean timerEnabled = homeViewModel.isTimerEnabled();

                if(!timerEnabled) {
                    int minutes = Integer.parseInt(textViewTimer.getText().toString().replace("'", ""));
                    int seconds = Integer.parseInt(textViewTimerSeconds.getText().toString().replace("'", ""));

                    int duration = 60 * minutes + seconds;

                    progressBar.setMax(duration);

                    homeViewModel.startTimer(duration);
                    buttonStartTimer.setImageResource(R.drawable.baseline_pause_24);
                }
                else{
                    homeViewModel.stopTimer();
                    buttonStartTimer.setImageResource(R.drawable.baseline_play_arrow_24);
                }
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.settingsFragment);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(homeViewModel.isTimerEnabled())
            homeViewModel.stopTimer();

        binding = null;
    }
}
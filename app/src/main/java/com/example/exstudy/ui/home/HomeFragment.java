package com.example.exstudy.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
        final ImageButton buttonStartTimer = binding.buttonHomeStartTimer;
        final ImageButton buttonSettings = binding.buttonHomeSettings;

        homeViewModel.getText().observe(getViewLifecycleOwner(), mTimerText -> {
            textViewTimer.setText(homeViewModel.getTimerString());
        });

        homeViewModel.getTimerTextSeconds().observe(getViewLifecycleOwner(), mTimerTextSeconds -> {
            textViewTimerSeconds.setText(homeViewModel.getTimerSecondsString());
        });

        getParentFragmentManager().setFragmentResultListener(SettingsFragment.RESULT_SETTINGS_FRAGMENT,
                this, (requestKey, result) -> {
                    Log.i("HOMEFRAGMENT", result.toString());
                    homeViewModel.setTimerMinutes(result.getString(SettingsFragment.MINUTES_KEY) + "'");
                    homeViewModel.setTimerSeconds(result.getString(SettingsFragment.SECONDS_KEY) + "''");
                });

        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean timerEnabled = homeViewModel.isTimerEnabled();

                if(!timerEnabled) {
                    int minutes = Integer.parseInt(textViewTimer.getText().toString().replace("'", ""));
                    int seconds = Integer.parseInt(textViewTimerSeconds.getText().toString().replace("'", ""));

                    homeViewModel.startTimer(60 * minutes + seconds);
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
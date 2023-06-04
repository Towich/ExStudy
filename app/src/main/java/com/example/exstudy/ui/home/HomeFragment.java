package com.example.exstudy.ui.home;

import static com.example.exstudy.ui.home.HomeDataSource.DEFAULT_MINUTES;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.exstudy.R;
import com.example.exstudy.databinding.FragmentHomeBinding;
import com.example.exstudy.ui.inventory.InventoryDataSource;
import com.example.exstudy.ui.settings.SettingsFragment;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HomeFragment extends Fragment {

    HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private TextView textViewTimer;
    private TextView textViewTimerSeconds;
    private ImageButton buttonStartTimer;
    private ImageButton buttonSettings;
    private ProgressBar progressBar;
    private ImageView seeds_image;
    private TextView seeds_name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize
        textViewTimer = binding.textHome;
        textViewTimerSeconds = binding.textHomeTimerSeconds;
        buttonStartTimer = binding.buttonHomeStartTimer;
        buttonSettings = binding.buttonHomeSettings;
        progressBar = binding.progressBarHome;

        seeds_image = binding.seedsImage;
        seeds_name = binding.seedsName;

        GetBundleFromSeedsInventory();

        // Timer: minutes
        homeViewModel.getText().observe(getViewLifecycleOwner(), mTimerText -> {
            String current_minutes = homeViewModel.getTimerString() + "'";
            textViewTimer.setText(current_minutes);
        });

        // Timer: seconds
        homeViewModel.getTimerTextSeconds().observe(getViewLifecycleOwner(), mTimerTextSeconds -> {
            String current_seconds = homeViewModel.getTimerSecondsString();

            int current_minutes_int = Integer.parseInt(homeViewModel.getTimerString());
            int current_seconds_int = Integer.parseInt(current_seconds);

            progressBar.setProgress(current_minutes_int * 60 + current_seconds_int);

            current_seconds += "''";
            textViewTimerSeconds.setText(current_seconds);
        });

        ConnectButtons();
        GetBundleSettings();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(homeViewModel.isTimerEnabled())
            homeViewModel.stopTimer();

        binding = null;
    }

    private void ConnectButtons(){

        // ProgressBar
        progressBar.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.inventoryFragment);
        });

        // StartTimer
        buttonStartTimer.setOnClickListener(view -> {
            boolean timerEnabled = homeViewModel.isTimerEnabled();

            if(!timerEnabled) {
                int minutes = Integer.parseInt(homeViewModel.getTimerString());
                int seconds = Integer.parseInt(homeViewModel.getTimerSecondsString());

                int duration = 60 * minutes + seconds;

                progressBar.setMax(duration);

                homeViewModel.startTimer(duration);
                buttonStartTimer.setImageResource(R.drawable.baseline_pause_24);
            }
            else{
                homeViewModel.stopTimer();
                buttonStartTimer.setImageResource(R.drawable.baseline_play_arrow_24);
                progressBar.setMax(Integer.parseInt(DEFAULT_MINUTES));
            }
        });

        // Settings
        buttonSettings.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.settingsFragment)
        );
    }
    private void GetBundleSettings(){
        getParentFragmentManager().setFragmentResultListener(SettingsFragment.RESULT_SETTINGS_FRAGMENT,
                this, (requestKey, result) -> {
                    final NumberFormat f = new DecimalFormat("00");

                    String minutes = result.getString(SettingsFragment.MINUTES_KEY);
                    String seconds = result.getString(SettingsFragment.SECONDS_KEY);

                    homeViewModel.setTimerMinutes(f.format(Integer.parseInt(minutes)));
                    homeViewModel.setTimerSeconds(f.format(Integer.parseInt(seconds)));

                    progressBar.setMax(Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds));
                });
    }

    private void GetBundleFromSeedsInventory(){

        Bundle seeds_bundle = getArguments();
        if(seeds_bundle != null){
            String mSeedsName = seeds_bundle.getString(InventoryDataSource.KEY_SEEDS_NAME);
            String mSeedsTimeToGrow = seeds_bundle.getString(InventoryDataSource.KEY_SEEDS_TIME_TO_GROW);
            int mSeedsImage = seeds_bundle.getInt(InventoryDataSource.KEY_SEEDS_IMAGE);

            Log.i("HOME", mSeedsName + " : " + mSeedsTimeToGrow + " : " + mSeedsImage);

            seeds_name.setText(mSeedsName);
            homeViewModel.setTimerMinutes(mSeedsTimeToGrow);
            seeds_image.setImageResource(mSeedsImage);
        }

    }
}
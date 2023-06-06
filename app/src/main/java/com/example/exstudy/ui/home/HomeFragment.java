package com.example.exstudy.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.exstudy.ui.inventory.seeds.InventorySeedsDataSource;

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
    private String seeds_minutes;

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
        // buttonSettings = binding.buttonHomeSettings;
        progressBar = binding.progressBarHome;

        seeds_image = binding.seedsImage;
        seeds_name = binding.seedsName;
        seeds_minutes = "30";

        GetBundleFromSeedsInventory();

        // Subscribe Timer: minutes
        homeViewModel.getText().observe(getViewLifecycleOwner(), mTimerText -> {
            String current_minutes = homeViewModel.getTimerString() + "'";
            textViewTimer.setText(current_minutes);
        });

        // Subscribe Timer: seconds
        homeViewModel.getTimerTextSeconds().observe(getViewLifecycleOwner(), mTimerTextSeconds -> {

            String current_minutes = homeViewModel.getTimerString();
            String current_seconds = homeViewModel.getTimerSecondsString();

            int current_minutes_int = Integer.parseInt(current_minutes);
            int current_seconds_int = Integer.parseInt(current_seconds);

            progressBar.setProgress(current_minutes_int * 60 + current_seconds_int);

            current_seconds += "''";
            textViewTimerSeconds.setText(current_seconds);
        });

        // Subscribe End Timer
        homeViewModel.getShowingPlantResult().observe(getViewLifecycleOwner(), mShowingPlantResult -> {
            // TODO
            seeds_image.setImageResource(R.drawable.lemon);
            seeds_name.setText("Ready to collect!");
            homeViewModel.setReadyToCollect(true);
            Log.i("HOME", "yes, changing.");
        });

        ConnectButtons();
        //GetBundleSettings();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(homeViewModel.isTimerEnabled())
            homeViewModel.stopTimer(seeds_minutes);

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
                homeViewModel.stopTimer(seeds_minutes);
                buttonStartTimer.setImageResource(R.drawable.baseline_play_arrow_24);
                progressBar.setMax(Integer.parseInt(seeds_minutes));

                // If the fruit has grown
                if(homeViewModel.isReadyToCollect()){
                    seeds_name.setText("Empty");
                    seeds_image.setImageResource(R.drawable.baseline_touch_app_24);
                    homeViewModel.collectPlant();
                    return;
                }
            }
        });

//        Settings
//        buttonSettings.setOnClickListener(view ->
//                Navigation.findNavController(view).navigate(R.id.settingsFragment)
//        );
    }

//    TO REMOVE
//    private void GetBundleSettings(){
//        getParentFragmentManager().setFragmentResultListener(SettingsFragment.RESULT_SETTINGS_FRAGMENT,
//                this, (requestKey, result) -> {
//                    final NumberFormat f = new DecimalFormat("00");
//
//                    String minutes = result.getString(SettingsFragment.MINUTES_KEY);
//                    String seconds = result.getString(SettingsFragment.SECONDS_KEY);
//
//                    homeViewModel.setTimerMinutes(f.format(Integer.parseInt(minutes)));
//                    homeViewModel.setTimerSeconds(f.format(Integer.parseInt(seconds)));
//
//                    progressBar.setMax(Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds));
//                });
//    }

    private void GetBundleFromSeedsInventory(){

        Bundle seeds_bundle = getArguments();
        if(seeds_bundle != null){
            String mSeedsName = seeds_bundle.getString(InventorySeedsDataSource.KEY_SEEDS_NAME);
            String mSeedsTimeToGrow = seeds_bundle.getString(InventorySeedsDataSource.KEY_SEEDS_TIME_TO_GROW);
            seeds_minutes = mSeedsTimeToGrow;
            int mSeedsImage = seeds_bundle.getInt(InventorySeedsDataSource.KEY_SEEDS_IMAGE);

            Log.i("HOME", mSeedsName + " : " + mSeedsTimeToGrow + " : " + mSeedsImage);

            seeds_name.setText(mSeedsName);
            homeViewModel.setTimerMinutes(mSeedsTimeToGrow);
            seeds_image.setImageResource(mSeedsImage);

            homeViewModel.setNameChosenSeeds(mSeedsName);
        }
    }
}
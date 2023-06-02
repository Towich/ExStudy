package com.example.exstudy.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.exstudy.R;
import com.example.exstudy.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    public final static String RESULT_SETTINGS_FRAGMENT = "RESULT_SETTINGS_FRAGMENT";

    public final static String MINUTES_KEY = "MINUTES_KEY";
    public final static String SECONDS_KEY = "SECONDS_KEY";
    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText edit_text_minutes = binding.editTextSettingsMinutes;
        final EditText edit_text_seconds = binding.editTextSettingsSeconds;
        final ImageButton button_to_home = binding.buttonSettingsBackToHome;

        button_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString(MINUTES_KEY, edit_text_minutes.getText().toString());
                bundle.putString(SECONDS_KEY, edit_text_seconds.getText().toString());

                getParentFragmentManager().setFragmentResult(RESULT_SETTINGS_FRAGMENT, bundle);


                Navigation.findNavController(view).navigate(R.id.navigation_home);
            }
        });
    }
}
package com.example.exstudy.ui.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.exstudy.databinding.FragmentTimerBinding;

public class TimerFragment extends Fragment {

    private FragmentTimerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TimerViewModel timerViewModel =
                new ViewModelProvider(this).get(TimerViewModel.class);

        binding = FragmentTimerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        timerViewModel.getText().observe(getViewLifecycleOwner(), newText -> {
            // updating the TextView of Counter
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
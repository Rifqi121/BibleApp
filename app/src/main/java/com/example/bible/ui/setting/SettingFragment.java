package com.example.bible.ui.setting;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.R;
import com.example.bible.databinding.FragmentSettingBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private FragmentSettingBinding binding;
    List<String> listDataHeader;
    MaterialCardView cardView;
    ImageButton show;
    LinearLayout hiddenLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cardView = root.findViewById(R.id.cardView);
        show = root.findViewById(R.id.button_show);
        hiddenLayout = root.findViewById(R.id.linear_layout);


        show.setOnClickListener(v -> {
            if (hiddenLayout.getVisibility() == root.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenLayout.setVisibility(root.GONE);
                show.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenLayout.setVisibility(root.VISIBLE);
                show.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }
        });


        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
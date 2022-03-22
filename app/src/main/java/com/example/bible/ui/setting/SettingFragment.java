package com.example.bible.ui.setting;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.R;
import com.example.bible.SettingPreferences;
import com.example.bible.databinding.FragmentSettingBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private FragmentSettingBinding binding;
    private MaterialCardView cardView;
    private ImageButton show;
    private LinearLayout hiddenLayout;
    private RadioGroup rgLanguage;
    private RadioButton rbEnglish;
    private RadioButton rbRussian;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cardView = root.findViewById(R.id.cardView);
        show = root.findViewById(R.id.button_show);
        hiddenLayout = root.findViewById(R.id.linear_layout);
        rgLanguage = root.findViewById(R.id.rgLanguage);
        rbEnglish = root.findViewById(R.id.radio_eng);
        rbRussian = root.findViewById(R.id.radio_rus);

        setDataSetting();

        rgLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                setLanguage();
            }
        });

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

    private void setLanguage() {
        int selectedId = rgLanguage.getCheckedRadioButtonId();
        String codeLocal = "en";

        if (selectedId == rbRussian.getId()) {
            codeLocal = "ba";
        }

        SettingPreferences.setCodeLanguage(getActivity().getBaseContext(), codeLocal);
        Snackbar.make(getActivity().findViewById(android.R.id.content), getResources().getString(R.string.please_restart), Snackbar.LENGTH_LONG).show();
    }

    private void setDataSetting() {
        String codeLang = SettingPreferences.getCodeLanguage(getActivity().getBaseContext());

        // Language
        if (codeLang.equals("en")) {
            rbEnglish.setChecked(true);
        }

        if (codeLang.equals("ba")) {
            rbRussian.setChecked(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.bible.ui.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.CursorLoader;

import com.example.bible.MusicPlayerActivity;
import com.example.bible.PodcastActivity;
import com.example.bible.R;
import com.example.bible.SermonsActivity;
import com.example.bible.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public CardView mCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CardView podcast = root.findViewById(R.id.podcast);
        CardView music = root.findViewById(R.id.musicPlayer);
        CardView sermons = root.findViewById(R.id.sermons);
        CardView bible = root.findViewById(R.id.bibleAudio);

        podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                podcast.getContext().startActivity(new Intent(podcast.getContext(), PodcastActivity.class));
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.getContext().startActivity(new Intent(music.getContext(), MusicPlayerActivity.class));
            }
        });


        sermons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sermons.getContext().startActivity(new Intent(sermons.getContext(), SermonsActivity.class));
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
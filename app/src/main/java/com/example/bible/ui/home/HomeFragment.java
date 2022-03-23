package com.example.bible.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.Album1Activity;
import com.example.bible.Album2Activity;
import com.example.bible.Album3Activity;
import com.example.bible.Album4Activity;
import com.example.bible.PodcastActivity;
import com.example.bible.R;
import com.example.bible.SermonsActivity;
import com.example.bible.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public CardView mCard;
    public CardView music;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CardView podcast = root.findViewById(R.id.podcast);
        music = root.findViewById(R.id.musicPlayer);
        CardView sermons = root.findViewById(R.id.sermons);
        CardView bible = root.findViewById(R.id.bibleAudio);

        PopupMenu popup = new PopupMenu(music.getContext(), music);

        podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                podcast.getContext().startActivity(new Intent(podcast.getContext(), PodcastActivity.class));
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity().getApplicationContext(), music);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.album1:
                                music.getContext().startActivity(new Intent(music.getContext(), Album1Activity.class));
                                break;

                            case R.id.album2:
                                music.getContext().startActivity(new Intent(music.getContext(), Album2Activity.class));
                                return true;

                            case R.id.album3:
                                music.getContext().startActivity(new Intent(music.getContext(), Album3Activity.class));
                                return true;

                            case R.id.album4:
                                music.getContext().startActivity(new Intent(music.getContext(), Album4Activity.class));
                                return true;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Clicked popup menu item " + menuItem.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        popup.show();


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

//    public void openPopupMenu(View view){
//
//        PopupMenu pm = new PopupMenu(getActivity().getApplicationContext(), music);
//        pm.getMenuInflater().inflate(R.menu.popup, pm.getMenu());
//        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.album1:
//                        Toast.makeText(getActivity().getApplicationContext(), "Clicked First Menu Item", Toast.LENGTH_SHORT).show();
//                        return true;
//
//                    case R.id.album2:
//                        Toast.makeText(getActivity().getApplicationContext(), "Clicked Second Menu Item", Toast.LENGTH_SHORT).show();
//                        return true;
//
//                    case R.id.album3:
//                        Toast.makeText(getActivity().getApplicationContext(), "Clicked Third Menu Item", Toast.LENGTH_SHORT).show();
//                        return true;
//                }
//
//                return true;
//            }
//        });
//        pm.show();
//
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
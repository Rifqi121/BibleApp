package com.example.bible.ui.grow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bible.R;

public class GrowFragment extends Fragment {

    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "COMING SOON", Toast.LENGTH_LONG).show();

        View v = inflater.inflate(R.layout.fragment_grow, container, false);

        return v;
    }
}

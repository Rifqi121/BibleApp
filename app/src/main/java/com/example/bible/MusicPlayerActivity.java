package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MusicPlayerActivity extends AppCompatActivity {

    public WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("https://sharikovministries.com/%d0%b1%d0%b5%d0%b7-%d1%82%d0%b5%d0%b1%d1%8f-%d1%82%d0%b5%d0%bc%d0%bd%d0%be-2/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

    }
}
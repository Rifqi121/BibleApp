package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.lang.reflect.InvocationTargetException;

public class Album1Activity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar spinner;
    private String VisibilityWebViewInitialUse = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album1);
        setTitle(R.string.album1);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("https://sharikovministries.com/%d0%b1%d0%b5%d0%b7-%d1%82%d0%b5%d0%b1%d1%8f-%d1%82%d0%b5%d0%bc%d0%bd%d0%be-2/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        spinner = findViewById(R.id.progressBar1);
        mWebView.setWebViewClient(new CustomWebViewClient());


    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            onPause();
        }else {
            super.onBackPressed();
        }
    }


    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView mWebView, String url, Bitmap favicon) {
            // only make it invisible the FIRST time the app is run
            if (VisibilityWebViewInitialUse.equals("show")) {
                mWebView.setVisibility(mWebView.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            VisibilityWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);

            view.setVisibility(mWebView.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        mWebView = null;
        super.onDestroy();
    }
}
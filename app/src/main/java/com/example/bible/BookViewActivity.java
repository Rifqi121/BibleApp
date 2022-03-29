package com.example.bible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class BookViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        String file = getIntent().getExtras().getString("Link");
        String title = getIntent().getExtras().getString("title");

        setTitle(title);
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(file).load();
    }
}
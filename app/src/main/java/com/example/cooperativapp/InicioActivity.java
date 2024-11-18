package com.example.cooperativapp;

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        VideoView videoView = findViewById(R.id.VideoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.videocooperativaa;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String videoUrl = "https://www.youtube.com/embed/8GYd5VCIR5A";
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(videoUrl);
    }
    public void onClickAcceder(View view) {
        Intent intent = new Intent(this, AccesoActivity.class);
        startActivity(intent);
    }
}

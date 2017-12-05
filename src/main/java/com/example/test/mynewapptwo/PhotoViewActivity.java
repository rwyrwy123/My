package com.example.test.mynewapptwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.test.mynewapptwo.adapter.RecycleTupianAdapter;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        photoView = findViewById(R.id.photo);
        Intent intent = getIntent();
        String urlImage = intent.getStringExtra("urlImage");
        Glide.with(this).load(urlImage).into(photoView);
    }
}

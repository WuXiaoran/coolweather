package com.coolweather.xiaoranas.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.coolweather.xiaoranas.R;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ImageListActivity extends AppCompatActivity {

    private PhotoView imageView;
    private String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);
        imagePath = getIntent().getStringExtra("image_url");
        imageView = (PhotoView) findViewById(R.id.imagelist_image);
        Glide.with(this).load(imagePath).into(imageView);
    }

}

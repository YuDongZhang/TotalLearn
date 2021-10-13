package com.example.totallearn.fragmentset.frag09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.netease.NeBigView;

import java.io.IOException;
import java.io.InputStream;

public class BigImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_img);
        NeBigView bigView = findViewById(R.id.bigView);
        InputStream is = null;
        try {
            is = getAssets().open("aaa.png");
            bigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
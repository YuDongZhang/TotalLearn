package com.example.totallearn.fragmentset.frag03.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.totallearn.fragmentset.frag03.paint.GradientLayout;

public class PaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GradientLayout(this));
    }
}
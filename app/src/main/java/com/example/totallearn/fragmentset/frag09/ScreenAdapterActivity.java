package com.example.totallearn.fragmentset.frag09;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.totallearn.R;

public class ScreenAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(),this);
        setContentView(R.layout.activity_screen_adapter);
    }
}

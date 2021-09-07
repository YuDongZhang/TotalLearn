package com.example.totallearn.fragmentset.frag03.colorfilter;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LightingColorFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ColorFilterView(this));
    }
}

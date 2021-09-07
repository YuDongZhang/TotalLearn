package com.example.totallearn.fragmentset.frag03.transform;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TransActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TransformView(this));
    }
}

package com.example.totallearn.fragmentset.frag03.paint;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class XfermodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new XfermodeEraserView(this));
    }
}

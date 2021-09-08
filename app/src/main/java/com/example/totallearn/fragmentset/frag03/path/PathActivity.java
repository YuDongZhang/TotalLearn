package com.example.totallearn.fragmentset.frag03.path;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.totallearn.R;

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new PathView(this));
//        setContentView(new BezierView(this));//多阶巴塞尔曲线
//        setContentView(R.layout.activity_path);//多阶巴塞尔曲线
        setContentView(new PathMeasureView(this));
    }
}

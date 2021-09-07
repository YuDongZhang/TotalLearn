package com.example.totallearn.fragmentset.frag03.transform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplitViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplitView(this));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
//        bitmap.getWidth();
//        bitmap.getHeight();
//        int pixel = bitmap.getPixel(0, 0);
    }
}

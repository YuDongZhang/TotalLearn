package com.example.totallearn.fragmentset.frag09.palyerriip;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.totallearn.R;
import com.example.totallearn.fragmentset.frag09.palyerriip.ui.UIUtils;
import com.example.totallearn.fragmentset.frag09.palyerriip.ui.ViewCalculateUtil;


public class WaterActivity extends AppCompatActivity {
    private ImageView imageView;
    RippleAnimationView rippleAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(this);
        setContentView(R.layout.activity_water);

//        imageView = (ImageView) findViewById(R.id.ImageView);
//        ViewCalculateUtil.setViewLayoutParam(imageView, 300,300,0,0,0,0);
//        rippleAnimationView = (RippleAnimationView) findViewById(R.id.layout_RippleAnimation);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (rippleAnimationView.isAnimationRunning()) {
//                    rippleAnimationView.stopRippleAnimation();
//                } else {
//                    rippleAnimationView.startRippleAnimation();
//                }
//            }
//        });
    }
}

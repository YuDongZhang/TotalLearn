package com.example.totallearn.base;

import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;

/**
 * Created by Shinelon on 2019/11/20.
 */

public class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG,"onResume");
    }
}

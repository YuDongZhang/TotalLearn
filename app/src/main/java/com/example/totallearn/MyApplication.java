package com.example.totallearn;

import android.annotation.SuppressLint;
import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;


public class MyApplication extends Application {
    public MyApplication() {
        Utils.init(this);

    }
}

package com.example.totallearn.ndkdemo;

/**
 * Created by Shinelon on 2019/12/18.
 */

public class NDKTools {

    static {
        System.loadLibrary("ndkdemotest-jni");
    }
    public static native String getStringFromNDK();
}

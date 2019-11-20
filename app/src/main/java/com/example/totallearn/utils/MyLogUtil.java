package com.example.totallearn.utils;

public class MyLogUtil {
    public static final String BIAOZHI = "TotalLearn";
    public static final boolean IS_PRINT_LOG = true;

    public MyLogUtil() {
    }

    public static void v(String tag, String msg) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void d(String tag, String msg) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void i(String tag, String msg) {
        android.util.Log.i("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.e("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }
}
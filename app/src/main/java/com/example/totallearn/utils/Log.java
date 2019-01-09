package com.example.totallearn.utils;

public class Log {
    public static final String TAG = "TotalLearn";
    public static final boolean IS_PRINT_LOG = true;

    public Log() {
    }

    public static void v(String tag, String msg) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void d(String tag, String msg) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void w(String tag, String msg) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }
}
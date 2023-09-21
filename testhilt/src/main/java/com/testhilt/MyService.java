package com.testhilt;

import android.util.Log;

import javax.inject.Inject;

public class MyService {
    
    @Inject
    public MyService() {
        // 构造函数注入
    }
    
    public void doSomething() {
        // 执行操作
        Log.d("MyService", "doSomething: ");
    }
}

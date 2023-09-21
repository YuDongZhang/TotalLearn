package com.testhilt;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MyViewModel extends ViewModel {

    private final MyService myService;

    @Inject
    public MyViewModel(MyService myService) {
        this.myService = myService;
    }

    public void doSomething() {
        // 使用 myService 执行操作
        Log.d("MyViewModel", "doSomething: ");
    }
}

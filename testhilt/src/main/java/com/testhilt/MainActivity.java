package com.testhilt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    //这个地方直接进行注入
    @Inject
    MyService myService;

    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 现在可以使用 myService 对象
        myService.doSomething();
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.doSomething();
    }
}
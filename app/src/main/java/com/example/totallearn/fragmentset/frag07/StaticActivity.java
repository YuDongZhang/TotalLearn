package com.example.totallearn.fragmentset.frag07;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.totallearn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaticActivity extends AppCompatActivity {

    //内存泄漏的几种情况的分析
    //1 . 定义静态变量把当前的activity , 静态变量没有清空，就会导致内存泄漏。static变量是贯穿整个应用的生命周期的，
    // 所以被泄漏的Activity就会一直存在于应用的进程中，不会被回收，同样的持有Activity（Context）的静态变量，比如View也是一样的道理

    private static Activity sActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        ButterKnife.bind(this);
        sActivity = this;
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }

    //非静态内部类 和 匿名类 都会潜在的引用它们所属的外部类，但是静态内部类却不会。


    private static Test sTest;
    private static Test2 sTest2;
    class Test {
    }

    static class Test2 {
    }

    private void test() {
        sTest = new Test();
        sTest2 = new Test2();
    }

//    1.如果这个非静态内部类实例内部做了一些耗时的操作，就会导致外围对象不会被回收，从而导致内存泄漏

    private void testRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}

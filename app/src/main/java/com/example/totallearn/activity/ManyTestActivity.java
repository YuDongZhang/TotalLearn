package com.example.totallearn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.totallearn.R;
import com.example.totallearn.TestActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 多种测试 - 用于学习 Perfetto
 * 包含启动耗时和多种内存泄漏场景
 */
public class ManyTestActivity extends AppCompatActivity {

    private static final String TAG = "ManyTestActivity";

    // 内存泄漏场景1: 静态变量持有 Activity 引用
    private static ManyTestActivity sLeakedActivity;

    // 内存泄漏场景2: 静态集合持有对象引用
    private static List<Runnable> sRunnableList = new ArrayList<>();

    private Handler mHandler;
    private Timer mTimer;
    private Runnable mLeakedRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_test);

        // 启动耗时测试1: 主线程耗时操作
        simulateStartupDelay();

        // 初始化按钮
        setupButtons();

        // 初始化 Handler
        mHandler = new MyHandler(this);

        // 内存泄漏场景1: 静态变量持有 Activity 引用
        sLeakedActivity = this;

        Log.d(TAG, "onCreate 完成");
    }

    /**
     * 模拟启动耗时 - 用于 Perfetto 分析应用启动性能
     */
    private void simulateStartupDelay() {
        long startTime = System.currentTimeMillis();
        Log.d(TAG, "开始模拟启动耗时");

        // 耗时操作1: 复杂计算
        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }

        // 耗时操作2: 字符串拼接
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("test").append(i);
        }

        // 耗时操作3: 对象创建
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Object());
        }

        long endTime = System.currentTimeMillis();
        Log.d(TAG, "启动耗时: " + (endTime - startTime) + "ms");
    }

    /**
     * 设置测试按钮
     */
    private void setupButtons() {
        Button btnLeak1 = findViewById(R.id.btn_leak_1);
        Button btnLeak2 = findViewById(R.id.btn_leak_2);
        Button btnLeak3 = findViewById(R.id.btn_leak_3);
        Button btnLeak4 = findViewById(R.id.btn_leak_4);
        Button btnJumpTest = findViewById(R.id.btn_jump_test);

        if (btnLeak1 != null) {
            btnLeak1.setOnClickListener(v -> leakScenario1());
        }
        if (btnLeak2 != null) {
            btnLeak2.setOnClickListener(v -> leakScenario2());
        }
        if (btnLeak3 != null) {
            btnLeak3.setOnClickListener(v -> leakScenario3());
        }
        if (btnLeak4 != null) {
            btnLeak4.setOnClickListener(v -> leakScenario4());
        }
        if (btnJumpTest != null) {
            btnJumpTest.setOnClickListener(v -> jumpToTestActivity());
        }
    }

    /**
     * 跳转到 TestActivity
     */
    private void jumpToTestActivity() {
        Log.d(TAG, "跳转到 TestActivity");
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
        finish();



    }

    /**
     * 内存泄漏场景1: Handler 导致的内存泄漏
     * Handler 持有 Activity 引用，且延迟发送消息，导致 Activity 无法被回收
     */
    private void leakScenario1() {
        Log.d(TAG, "触发 Handler 内存泄漏");
        // 发送延迟消息，Handler 持有 Activity 引用
        mHandler.sendMessageDelayed(Message.obtain(), 10000);
    }

    /**
     * 内存泄漏场景2: 静态集合持有 Runnable 引用，Runnable 持有 Activity 引用
     */
    private void leakScenario2() {
        Log.d(TAG, "触发静态集合内存泄漏");
        // 创建匿名内部类 Runnable，持有 Activity 引用
        mLeakedRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Leaked runnable running");
            }
        };
        // 添加到静态集合，导致 Activity 无法被回收
        sRunnableList.add(mLeakedRunnable);
    }

    /**
     * 内存泄漏场景3: Timer 导致的内存泄漏
     * Timer 持有 Activity 引用，且 TimerTask 未被取消
     */
    private void leakScenario3() {
        Log.d(TAG, "触发 Timer 内存泄漏");
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "Timer task running");
            }
        }, 1000, 1000);
    }

    /**
     * 内存泄漏场景4: 匿名内部类持有 Activity 引用
     * 匿名内部类隐式持有外部类 Activity 的引用
     */
    private void leakScenario4() {
        Log.d(TAG, "触发匿名内部类内存泄漏");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 线程休眠，保持对 Activity 的引用
                    Thread.sleep(30000);
                    Log.d(TAG, "Thread finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 自定义 Handler 类 - 可能导致内存泄漏
     */
    private static class MyHandler extends Handler {
        private final ManyTestActivity mActivity;

        public MyHandler(ManyTestActivity activity) {
            super(Looper.getMainLooper());
            mActivity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity != null) {
                Log.d(TAG, "Handler 处理消息");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        // 清理 Handler
//        if (mHandler != null) {
//            mHandler.removeCallbacksAndMessages(null);
//        }

        // 清理 Timer
//        if (mTimer != null) {
//            mTimer.cancel();
//            mTimer = null;
//        }

        // 清理静态变量引用（实际开发中应该这样做，但为了演示泄漏，这里不清理）
        // sLeakedActivity = null;
        // sRunnableList.clear();
    }
}
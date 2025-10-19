package com.example.totallearn.time_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.totallearn.R;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeActivity extends AppCompatActivity {

    private TextView timeTv;

    private int recLen = 0;
    private volatile boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        
        // 初始化视图
        timeTv = findViewById(R.id.time_tv);
        
        // 设置点击事件
        findViewById(R.id.time_bt1).setOnClickListener(this::onViewClicked);
        findViewById(R.id.time_bt2).setOnClickListener(this::onViewClicked);
        
        isStart = true;
        new Thread(new MyThread()).start();

    }


    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {          // handle
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recLen++;
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));//不加这个 默认就是东 八 区 出来就有时间
                    String time = sdf.format(recLen * 1000);
                    timeTv.setText(time);
            }
            super.handleMessage(msg);
        }
    };

    private void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.time_bt1) {
            isStart = false;

//                isStart=true;
//                new Thread(new MyThread()).start();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recLen = 0;
                    isStart=true;
                    new Thread(new MyThread()).start();
                }
            },100);
        } else if (id == R.id.time_bt2) {
//                isStart = false;
            isStart=true;
            new Thread(new MyThread()).start();
        }
    }


    public class MyThread implements Runnable {      // thread
        @Override
        public void run() {
            while (isStart) {
                try {
                    Thread.sleep(1000);     // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }

            }
        }
    }
}
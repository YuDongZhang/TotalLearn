package com.example.totallearn.time_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.totallearn.R;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeActivity extends AppCompatActivity {

    @BindView(R.id.time_tv)
    TextView timeTv;

    private int recLen = 0;
    private volatile boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);
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

    @OnClick({R.id.time_bt1, R.id.time_bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.time_bt1:
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

                break;

            case R.id.time_bt2:
//                isStart = false;
                isStart=true;
                new Thread(new MyThread()).start();
                break;
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
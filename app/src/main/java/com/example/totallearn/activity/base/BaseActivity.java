package com.example.totallearn.activity.base;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Shinelon on 2019/11/20.
 */

public class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG,"onResume");
    }

    private Timer mTimer; // 计时器，每1秒执行一次任务
    private  MyTimerTask mTimerTask; // 计时任务，判断是否未操作时间到达ns
    public static  long mLastActionTime; // 上一次操作时间
    private boolean isTime;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG,"msg  "+msg.what);
            resetSprfMain();
        }
    };

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Log.e(TAG, "计时中……"+mLastActionTime);
            // 5分钟未操作停止计时并退出登录
            if (System.currentTimeMillis() - mLastActionTime > 1000 * 10  ) {
                Log.e(TAG, "10秒到了");
                stopTimer();// 停止计时任务
                handler.sendEmptyMessage(1);
                // resetSprfMain();//退出登录状态
            }
        }
    }

    // 登录成功，开始计时
    public void startTimer() {
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        // 初始化上次操作时间为登录成功的时间
        mLastActionTime = System.currentTimeMillis();
        // 每过1s检查一次
        mTimer.schedule(mTimerTask, 0, 1000);
        //Log.e(TAG, "登录成功开始计时");
    }

    public void resetSprfMain() {
        LogUtils.d(TAG,"");
 AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("温馨提示")
                    .setCancelable(false)
                    .setMessage("当前登录已失效，请重新登录")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           // App.getInstance().destroyActivity();

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        /*TestDialogFragment dialogFragment = new TestDialogFragment();
        dialogFragment.showAllowingLoss(getSupportFragmentManager(),"11");*/

    }



    // 停止计时任务
    public void stopTimer() {
        mTimer.cancel();
        //Log.e("", "取消计时");
    }





}

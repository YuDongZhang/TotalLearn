package com.example.totallearn.serviceset;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.totallearn.TestActivity;

/**
 *
 * Created by Shinelon on 2019/1/6.
 */

public class TestService extends Service{

    public static final String TAG = TestService.class.getSimpleName();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        Intent intent1 = new Intent(getBaseContext(), TestActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent1);//在服务中启动 activity
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

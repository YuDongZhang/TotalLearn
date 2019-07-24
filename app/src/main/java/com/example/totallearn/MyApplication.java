package com.example.totallearn;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.totallearn.dagger_learn.register_login_6.AppComponent;
import com.example.totallearn.dagger_learn.register_login_6.AppMoudle;
import com.example.totallearn.dagger_learn.register_login_6.DaggerAppComponent;


/**
 * 3. 在MyApplication实例化AppComponent：
 */
public class MyApplication extends Application {

    private AppComponent mAppComponent;

    public MyApplication() {
        Utils.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //在写的时候看到  包 4中也有一个 DaggerAppComponent, 这个应该是遗留下来的 , 现在用的 是包 6中
        mAppComponent = DaggerAppComponent.builder().appMoudle(new AppMoudle(this)).build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}

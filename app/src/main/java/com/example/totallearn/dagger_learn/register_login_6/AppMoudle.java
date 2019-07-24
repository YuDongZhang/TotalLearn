package com.example.totallearn.dagger_learn.register_login_6;

import android.util.Log;

import com.example.totallearn.MyApplication;
import com.example.totallearn.dagger_learn.register_login.ApiService;
import com.example.totallearn.dagger_learn.register_login_4.ApiService4;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/7/23.
 * 1. 创建全局AppModule：
 *
 *
 */
@Module
public class AppMoudle {
    private MyApplication context;

    public AppMoudle(MyApplication myApplication){
        this.context = myApplication;
    }

    @Singleton
    @Provides
    public ApiService6 provideApiService6(){
        ApiService6 apiService6 = new ApiService6(context);
        Log.d("TAG", "provideApiService: " + apiService6);
        return apiService6;
    }
}

package com.example.totallearn.dagger_learn.register_login_6;


import com.example.totallearn.dagger_learn.register_login_4.ApiService4;
import com.example.totallearn.dagger_learn.register_login_4.PerActivity;
import com.example.totallearn.dagger_learn.register_login_4.RegisterLogin_4Activity;

import javax.inject.Inject;

import dagger.Component;

/*
和上一个对比 , 发现添加的一些东西
 */
@PerActivity
@Component(modules = {UserModule6.class},dependencies = AppComponent.class)
public interface UserComponet6 {
    // 在复制这个的时候的 点击锤子 , 发现这个地方出错 , 一直在报 apiservice4 构造方法没有inject 在跟踪到
//    RegisterLogin_4Activity 中发现了 @Inject ApiService4 apiService4_1; 应该就是这个东西引起的 , 所以在
//    此处跟换 activity6 系列
   // void inject(RegisterLogin_4Activity activity);
    void inject(RegisterLogin_6Activity activity);
}
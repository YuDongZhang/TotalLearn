package com.example.totallearn.dagger_learn.register_login_6;

import com.example.totallearn.dagger_learn.register_login_4.Login_4Activity;
import com.example.totallearn.dagger_learn.register_login_4.PerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2019/7/22.
 * 可以和上一个来进行对比
 * 发现添加的一些东西
 */
@PerActivity
@Component(modules = UserModule6.class,dependencies = AppComponent.class)
public interface UserCLoginComponet2 {
    //这里 和 userComponet6 中描述的是一样的  也是跟换 为 activity6 系列中的 activity
//    void inject(Login_4Activity activity);
    void inject(Login_6Activity activity);
}

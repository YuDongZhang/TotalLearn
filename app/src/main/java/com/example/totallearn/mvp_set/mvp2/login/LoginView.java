package com.example.totallearn.mvp_set.mvp2.login;

import com.example.totallearn.mvp_set.mvp2.MvpView;

/**
 * 作者: Dream on 2018/3/9 21:18
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//V层->抽象同事B
public interface LoginView extends MvpView {

    void onLoginResult(String result);

}

package com.example.totallearn.mvp_set.mvp2.login;


import com.example.totallearn.mvp_set.demo1.utills.HttpUtils;
import com.example.totallearn.mvp_set.mvp2.MvpPresenter;
import com.example.totallearn.mvp_set.mvp2.impl.MvpBasePresenter;

/**
 * 作者: Dream on 2018/3/9 21:23
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//具体中介
public class LoginPresenter extends MvpBasePresenter<LoginView> {

    //持有同事引用
    //两个同事：M层、V层
    private LoginModel model;


    public LoginPresenter(){
        this.model = new LoginModel();
    }



    public void login(String username, String password){
        this.model.login(username, password, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                if (getView() != null){
                    getView().onLoginResult(result);
                }
            }
        });
    }

}

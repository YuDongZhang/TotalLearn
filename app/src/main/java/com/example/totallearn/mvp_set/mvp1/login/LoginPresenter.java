package com.example.totallearn.mvp_set.mvp1.login;


import com.example.totallearn.mvp_set.demo1.utills.HttpUtils;

/**
 * 作者: Dream on 2018/3/9 21:23
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//具体中介
public class LoginPresenter  {

    //持有同事引用
    //两个同事：M层、V层
    private LoginModel model;
    private LoginView view;

    public LoginPresenter(){
        this.model = new LoginModel();
    }

    public void attachView(LoginView view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }

    public void login(String username, String password){
        this.model.login(username, password, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                if (view != null){
                    view.onLoginResult(result);
                }
            }
        });
    }

}

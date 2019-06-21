package com.example.totallearn.dagger_learn.mvp_login;

import android.content.Context;
import android.widget.Toast;

public class LoginPresenter
{
    ICommonView iView;

 
    public LoginPresenter(ICommonView iView){

        this.iView = iView;
    }

    public void login(User user){

        Context mContext = iView.getContext();
        Toast.makeText(mContext,"login......",Toast.LENGTH_SHORT).show();
    }
}
package com.example.totallearn.dagger_learn.register_login;

import android.util.Log;

import javax.inject.Inject;

public class ApiService {

    @Inject
    public ApiService() {
    }

    public void register() {
        //注册的方法
         Log.i("TAG", "ApiService: ");
    }

}
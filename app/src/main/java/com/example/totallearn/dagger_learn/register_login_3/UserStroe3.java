package com.example.totallearn.dagger_learn.register_login_3;

import android.util.Log;

import javax.inject.Inject;

public class UserStroe3 {

    @Inject
    public UserStroe3(String url) {
        Log.d("TAG", "UserStroe: " + url);
    }

    public void login3() {
        Log.d("TAG", "UserStroe: ");
    }
}
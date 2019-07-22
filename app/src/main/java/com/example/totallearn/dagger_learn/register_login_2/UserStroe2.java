package com.example.totallearn.dagger_learn.register_login_2;

import android.util.Log;

import javax.inject.Inject;

public class UserStroe2 {

    @Inject
    public UserStroe2(String url) {
        Log.d("TAG", "UserStroe: " + url);
    }

    public void login2() {
        Log.d("TAG", "UserStroe: ");
    }
}
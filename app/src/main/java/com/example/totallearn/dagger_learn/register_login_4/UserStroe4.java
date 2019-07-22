package com.example.totallearn.dagger_learn.register_login_4;

import android.util.Log;

import javax.inject.Inject;

public class UserStroe4 {

    @Inject
    public UserStroe4(String url) {
        Log.d("TAG", "UserStroe: " + url);
    }

    public void login4() {
        Log.d("TAG", "UserStroe: ");
    }
}
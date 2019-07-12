package com.example.totallearn.dagger_learn.register_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.dagger_learn.register_login.cp.DaggerUserComponet;

//https://cloud.tencent.com/developer/article/1334811
public class RegisterLoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        /*
        未用dagger 之前的方法
        ApiService apiService = new ApiService();
        apiService.register();*/
        DaggerUserComponet.create().inject(this);



    }
}

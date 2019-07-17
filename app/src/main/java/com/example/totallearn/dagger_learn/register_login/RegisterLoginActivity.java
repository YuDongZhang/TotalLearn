package com.example.totallearn.dagger_learn.register_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.dagger_learn.register_login.cp.DaggerUserComponet;
import com.example.totallearn.dagger_learn.register_login.md.UserModule;
import com.example.totallearn.dagger_learn.register_login.md.UserModule2;

import javax.inject.Inject;

//https://cloud.tencent.com/developer/article/1334811
public class RegisterLoginActivity extends AppCompatActivity {

    @Inject
    ApiService mApiService;

    @Inject
    UserManager2 mManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        /*
        未用dagger 之前的方法
        ApiService apiService = new ApiService();
        apiService.register();*/

        //无参数添加的 dagger2
        DaggerUserComponet.create().inject(this);
        mApiService.register();

        // 修改加参数的 dagger2
        /*DaggerUserComponet.builder().userModule2(new UserModule2(this)).build().inject(this);
        mManager.register();*/

    }
}

package com.example.totallearn.dagger_learn.register_login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.dagger_learn.register_login_2.UserManager2;
import com.example.totallearn.dagger_learn.register_login_3.ApiService3;
import com.example.totallearn.dagger_learn.register_login_3.DevTest;
import com.example.totallearn.dagger_learn.register_login_3.ReleaseTest;

import javax.inject.Inject;
import javax.inject.Named;

//https://cloud.tencent.com/developer/article/1334811
public class RegisterLoginActivity extends AppCompatActivity {

    @Inject
    ApiService mApiService;

    @Inject
    UserManager2 mManager2;

    //方法3 标记出
    @Named("dev")
    @Inject
    ApiService3 apiService3_1;

    @Named("release")
    @Inject
    ApiService3 apiService3_2;

    @DevTest
    @Inject
    ApiService3 apiService3_3;

    @ReleaseTest
    @Inject
    ApiService3 apiService3_4;

    private boolean is_Dev = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        /*
        未用dagger 之前的方法
        ApiService apiService = new ApiService();
        apiService.register();*/

        //无参数添加的 dagger2
        //DaggerUserComponet.create().inject(this);
        //mApiService.register();

        // 修改加参数的 dagger2
       /* DaggerUserComponet.builder().userModule2(new UserModule2(this)).build().inject(this);
        mManager2.register2();*/

        //第 3 套的设计  , 主要是在 usermodule3中的修改
        /*DaggerUserComponet.builder().userModule3(new UserModule3(this)).build().inject(this);
        apiService3_1.register3();
        apiService3_2.register3();*/




    }
}

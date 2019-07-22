package com.example.totallearn.dagger_learn.register_login_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;

import javax.inject.Inject;

public class RegisterLogin_4Activity extends AppCompatActivity {

    @Inject
    ApiService4 apiService4_1;

    @Inject
    ApiService4 apiService4_2; //通过测试 发现两者的 hashcode 是一样的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login_4);




    }
}

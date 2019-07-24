package com.example.totallearn.dagger_learn.register_login_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.MyApplication;
import com.example.totallearn.R;
import com.example.totallearn.dagger_learn.register_login_4.DaggerUserCLoginComponet;

import javax.inject.Inject;

public class Login_6Activity extends AppCompatActivity {

    @Inject
    ApiService6 apiService6;//这个是后来加的看是否会打印

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_6);
// 6.调用
        DaggerUserCLoginComponet2.builder().
                appComponent(((MyApplication)getApplicationContext()).getAppComponent()).
                build().inject(this);

        // 这里可以在 appmudle 中来进行查看到的 打印 , 包括上面的那个 打印都来看看 是否可以使用 . 
    }
}

package com.example.totallearn.dagger_learn.register_login_6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.MyApplication;
import com.example.totallearn.R;

public class RegisterLogin_6Activity extends AppCompatActivity {

    /*//这个地方加入 也会报错 , 看得重新探索 全局单例的使用
    @Inject
    ApiService6 apiService6;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login_6);

//        6. 调用
//        DaggerUserComponet6
//                .builder()
//                .appComponent((MyApplication)getApplicationContext().ge

        DaggerUserComponet6.builder().
                appComponent(((MyApplication)getApplicationContext()). getAppComponent()).
                build().inject(this);

    }
}

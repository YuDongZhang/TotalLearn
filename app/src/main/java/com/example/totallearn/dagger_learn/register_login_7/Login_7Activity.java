package com.example.totallearn.dagger_learn.register_login_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.MyApplication;
import com.example.totallearn.R;
import com.example.totallearn.utils.MyLogUtil;

import javax.inject.Inject;

public class Login_7Activity extends AppCompatActivity {

    @Inject
    UserStroe7 mUserStroe7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_7);

        ((MyApplication)getApplicationContext()).getFComponent().addCComponent().Inject(this);
        MyLogUtil.d("Login_7Activity", "onCreate: "+mUserStroe7);

        /**
         * 首先我们先创建FComponent，他属于App级别的。我们在MyApplication创建它。FComponent中调用者提供CComponent。
         * 然后有CComponent。这和我们之前使用有些不同。之前我们都是通过Avtivity级别创建，然后填入App级别的参数。
         * 这个使用正好相反。优势：

         不需要在被依赖的Component显示提供依赖
         不需要使用更多的DaggerXXXXComponent对象来创建依赖，仅需要在被依赖Component中增加 XXXComponent addXXCom
         */

    }
}

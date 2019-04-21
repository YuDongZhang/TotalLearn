package com.example.totallearn.mvp_set.mvp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.totallearn.R;
import com.example.totallearn.mvp_set.mvp2.login.LoginPresenter;
import com.example.totallearn.mvp_set.mvp2.login.LoginView;
import com.example.totallearn.mvp_set.mvp2.impl.MvpActivity;

//中介者模式 -> 同事
public class LoginActivity2 extends MvpActivity<LoginView,LoginPresenter> implements LoginView{
    //这里需要一个服务器



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }



    public void clickLogin(View v){
        getPresenter().login("Drem","123456");
    }

    @Override
    public void onLoginResult(String result) {//这个地方就是回调
        Toast.makeText(this,"返回内容",Toast.LENGTH_SHORT).show();
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

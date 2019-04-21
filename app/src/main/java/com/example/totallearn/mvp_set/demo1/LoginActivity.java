package com.example.totallearn.mvp_set.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.totallearn.R;
import com.example.totallearn.mvp_set.demo1.utills.HttpTask;
import com.example.totallearn.mvp_set.demo1.utills.HttpUtils;
import com.example.totallearn.mvp_set.mvp1.login.LoginPresenter;
import com.example.totallearn.mvp_set.mvp1.login.LoginView;

import java.util.HashMap;
import java.util.Map;

//中介者模式 -> 同事
public class LoginActivity extends AppCompatActivity implements LoginView{
    //这里需要一个服务器

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.presenter = new LoginPresenter();
        this.presenter.attachView(this);
    }

    /*
    初级的写代码
    public void clickLogin(View v){
        Map<String, Object> params = new HashMap<>();
        params.put("username", "Dream");
        params.put("password", "123456");
        new HttpTask.Builder()
                .method("POST")
                .url("")
                .paramMap(params)
                .onHttpResultListener(new HttpUtils.OnHttpResultListener() {
                    @Override
                    public void onResult(String result) {
                        Toast.makeText(LoginActivity.this,"返回内容"+ result,Toast.LENGTH_SHORT).show();
                    }
                }).build().execute();
    }*/

    public void clickLogin(View v){
        this.presenter.login("Drem","123456");
    }

    @Override
    public void onLoginResult(String result) {//这个地方就是回调
        Toast.makeText(this,"返回内容",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}

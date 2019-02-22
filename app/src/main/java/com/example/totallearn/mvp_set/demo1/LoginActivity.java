package com.example.totallearn.mvp_set.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.totallearn.R;
import com.example.totallearn.mvp_set.demo1.utills.HttpTask;
import com.example.totallearn.mvp_set.demo1.utills.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    //这里需要一个服务器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

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
    }
}

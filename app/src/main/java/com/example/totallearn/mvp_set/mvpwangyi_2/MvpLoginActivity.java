package com.example.totallearn.mvp_set.mvpwangyi_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.totallearn.R;
import com.example.totallearn.mvp_set.mvpwangyi_2.base.BaseView;
import com.example.totallearn.mvp_set.mvpwangyi_2.bean.UserInfo;
import com.example.totallearn.mvp_set.mvpwangyi_2.login.LoginContract;
import com.example.totallearn.mvp_set.mvpwangyi_2.login.LoginPresenter;


public class MvpLoginActivity extends BaseView<LoginPresenter, LoginContract.View> {

    private EditText nameEt;
    private EditText pwdEt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvp);

        initView();
    }

    // 初始化控件
    private void initView() {
        nameEt = findViewById(R.id.et_name);
        pwdEt = findViewById(R.id.et_pwd);
        btn = findViewById(R.id.bt_login);
    }

    // 点击事件
    public void doLoginAction(View view) {
        String name = nameEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        // 发起需求，让Presenter处理
        p.getContract().requestLogin(name, pwd);
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {//这是一个面向接口的过程
            @Override
            public void handlerResult(UserInfo userInfo) {
                if (userInfo != null) {
                    Toast.makeText(MvpLoginActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MvpLoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}

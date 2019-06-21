package com.example.totallearn.dagger_learn.mvp_login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.totallearn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginDaggerActivity extends AppCompatActivity implements ICommonView {

    @BindView(R.id.btn)
    Button btn;

    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dagger);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {

    }
}

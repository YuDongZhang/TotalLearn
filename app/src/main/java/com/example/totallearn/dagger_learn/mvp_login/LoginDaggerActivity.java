package com.example.totallearn.dagger_learn.mvp_login;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.totallearn.R;
import com.example.totallearn.databinding.ActivityLoginDaggerBinding;

public class LoginDaggerActivity extends AppCompatActivity implements ICommonView {

    private ActivityLoginDaggerBinding binding;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginDaggerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mLoginPresenter = new LoginPresenter(this);

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClicked();
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void onViewClicked() {

    }
}
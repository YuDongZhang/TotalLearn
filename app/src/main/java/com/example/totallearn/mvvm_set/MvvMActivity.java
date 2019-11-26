package com.example.totallearn.mvvm_set;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.databinding.ActivityMvvMBinding;

public class MvvMActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvMBinding activityMvvMBinding = DataBindingUtil.setContentView(this,R.layout.activity_mvv_m);
        UserBean user = new UserBean();
        activityMvvMBinding.setUser(user);
    }
}

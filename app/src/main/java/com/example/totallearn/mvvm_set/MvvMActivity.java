package com.example.totallearn.mvvm_set;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;
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

package com.example.totallearn.mvvm_set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;
import com.example.totallearn.databinding.ActivityMvvMBinding;

public class MvvMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvMBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_mvv_m);
        UserBean user = new UserBean();
        binding.setUser(user);

        // 初始化 ViewModel
        CounterViewModel viewModel = new ViewModelProvider(this).get(CounterViewModel.class);
        // 将 ViewModel 与布局绑定
        binding.setViewModel(viewModel);
        // 设置此行可确保 LiveData 更新 UI
        binding.setLifecycleOwner(this);
    }
}

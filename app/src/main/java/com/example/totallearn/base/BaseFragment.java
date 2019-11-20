package com.example.totallearn.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;


public class BaseFragment extends Fragment {

    protected final String TAG = getClass().getName();
    public String ceshi;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        LogUtils.d(TAG,"onResume");
        super.onResume();

    }
}

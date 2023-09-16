package com.example.totallearn.activity.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

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

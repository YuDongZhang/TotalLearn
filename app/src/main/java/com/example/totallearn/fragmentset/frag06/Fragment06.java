package com.example.totallearn.fragmentset.frag06;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.example.totallearn.R;
import com.example.totallearn.testRoom.MultiFunctionActivity;



/**
 * Created by pateo on 18-12-27.
 */

public class Fragment06 extends Fragment {

    public static final String TAG = Fragment06.class.getSimpleName();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_06, container, false);
        
        // 设置点击事件
        setClickListeners(view);
        
        return view;
    }
    
    private void setClickListeners(View view) {
        // 为按钮设置点击事件
        if (view.findViewById(R.id.f6_t1) != null) {
            view.findViewById(R.id.f6_t1).setOnClickListener(this::onViewClicked);
        }
        if (view.findViewById(R.id.room) != null) {
            view.findViewById(R.id.room).setOnClickListener(this::onViewClicked);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    private void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.f6_t1) {
            LogUtils.d("f6测试点击了");
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        } else if (id == R.id.room) {
            Intent intent = new Intent(getActivity(), MultiFunctionActivity.class);
            startActivity(intent);
        }
    }
}

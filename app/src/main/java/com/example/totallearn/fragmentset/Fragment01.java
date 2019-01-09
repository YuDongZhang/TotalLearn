package com.example.totallearn.fragmentset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.totallearn.R;
import com.example.totallearn.serviceset.TestService;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment01 extends Fragment implements View.OnClickListener {

    public static final String TAG = Fragment01.class.getSimpleName();
    private Button f01bt1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");

        /**
         * 测试 看看
         */
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_01,container,false);
        f01bt1 = view.findViewById(R.id.f1_bt1);
        f01bt1.setOnClickListener(this);//设置监听一定要set 低级的错误不能再犯
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.f1_bt1:
                Log.d(TAG,"f1_bt1");
                //在service 中启动服务要 getActivity
                Intent intent = new Intent(getActivity(), TestService.class);
                getActivity().startService(intent);
                break;
            case R.id.f1_bt2:
                Log.d(TAG,"f1_bt1");
                //在service 中启动服务要 getActivity
                Intent intent1 = new Intent(getActivity(), TestService.class);
                getActivity().stopService(intent1);
                break;

        }
    }
}

package com.example.totallearn.fragmentset.frag09;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.totallearn.R;
import com.example.totallearn.TestActivity;
import com.example.totallearn.fragmentset.adapter.Frag09Adapter;
import com.example.totallearn.fragmentset.frag09.login.AspectMainActivity;
import com.example.totallearn.fragmentset.frag09.palyerriip.WaterActivity;
import com.example.totallearn.mvp_set.mvpwangyi.WYmvpActivity;
import com.example.totallearn.mvp_set.mvpwangyi_2.MvpLoginActivity;
import com.example.totallearn.mvvm_set.MvvMActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment09 extends Fragment {

    public static final String TAG = Fragment09.class.getSimpleName();


    public static String[] data = {
            "0.自定义像素适配",
            "1.MaterialDesign,自定义的recycler",
            "2.淘宝的 vlayout",
            "3.网易水波纹的效果实现",
            "4.aspect集中式登录架构设计",
            "5.1.2.2 MVP思想精髓与解耦",
            "6.1.2.3 MVP思想实现项目基础框架搭建",
            "7.mvvm基础",
            "8.eventbus测试,正确使用"

    };


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
        View view = inflater.inflate(R.layout.fragment_09, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        Frag09Adapter adapter = new Frag09Adapter(data);
        adapter.setOnItemClickListener(new Frag09Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        Intent intent = new Intent(getActivity(), ScreenAdapterActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(getActivity(), CustomRecyclerActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), VLayoutActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(getActivity(), WaterActivity.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(getActivity(), AspectMainActivity.class);
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(getActivity(), WYmvpActivity.class);
                        startActivity(intent);
                        break;

                    case 6:
                        intent = new Intent(getActivity(), MvpLoginActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(getActivity(), MvvMActivity.class);
                        startActivity(intent);
                        break;

                    case 8:
                        intent = new Intent(getActivity(), TestActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
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


}

package com.example.totallearn.fragmentset.frag03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.totallearn.R;
import com.example.totallearn.TestActivity;
import com.example.totallearn.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment03 extends Fragment {

    public static final String TAG = Fragment03.class.getSimpleName();
    Unbinder unbinder;


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
        View view = inflater.inflate(R.layout.fragment_03, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
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
        unbinder.unbind();
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


    @OnClick({R.id.f3_b1, R.id.f3_b2, R.id.f3_tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.f3_b1:
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
                break;

            case R.id.f3_b2:
//                FanSheTest fst = new FanSheTest();
//                startActivity(new Intent(getActivity(), MainActivity.class));

                break;

            case R.id.f3_tv3:
                intent = new Intent(getActivity(), MaterialDesignActivity.class);
                startActivity(intent);
                break;
        }
    }
}

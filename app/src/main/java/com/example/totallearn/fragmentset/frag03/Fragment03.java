package com.example.totallearn.fragmentset.frag03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.totallearn.R;
import com.example.totallearn.TestActivity;
import com.example.totallearn.fragmentset.frag03.colorfilter.LightingColorFilterActivity;
import com.example.totallearn.fragmentset.frag03.paint.PaintActivity;
import com.example.totallearn.fragmentset.frag03.paint.XfermodeActivity;
import com.example.totallearn.fragmentset.frag03.paint.XfermodesActivity;
import com.example.totallearn.fragmentset.frag03.path.PathActivity;
import com.example.totallearn.fragmentset.frag03.transform.SplashActivity;
import com.example.totallearn.fragmentset.frag03.transform.SplitViewActivity;
import com.example.totallearn.fragmentset.frag03.transform.TransActivity;



/**
 * Created by pateo on 18-12-27.
 */

public class Fragment03 extends Fragment {

    public static final String TAG = Fragment03.class.getSimpleName();



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
        
        // 设置所有点击事件
        setClickListeners(view);
        
        return view;
    }
    
    private void setClickListeners(View view) {
        // 为所有按钮设置点击事件
        view.findViewById(R.id.f3_b1).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_b2).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv3).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv4).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv5).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv6).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv7).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv8).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv9).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv10).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv11).setOnClickListener(this::onViewClicked);
        view.findViewById(R.id.f3_tv12).setOnClickListener(this::onViewClicked);
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
        Intent intent;
        
        if (id == R.id.f3_b1) {
            intent = new Intent(getActivity(), TestActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_b2) {
            // FanSheTest fst = new FanSheTest();
            // startActivity(new Intent(getActivity(), MainActivity.class));
        } else if (id == R.id.f3_tv3) {
            intent = new Intent(getActivity(), MaterialDesignActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv4) {
            // do nothing
        } else if (id == R.id.f3_tv5) {
            intent = new Intent(getActivity(), PaintActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv6) {
            intent = new Intent(getActivity(), XfermodeActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv7) {
            intent = new Intent(getActivity(), XfermodesActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv8) {
            intent = new Intent(getActivity(), LightingColorFilterActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv9) {
            intent = new Intent(getActivity(), TransActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv10) {
            intent = new Intent(getActivity(), SplitViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv11) {
            intent = new Intent(getActivity(), SplashActivity.class);
            startActivity(intent);
        } else if (id == R.id.f3_tv12) {
            intent = new Intent(getActivity(), PathActivity.class);
            startActivity(intent);
        }
    }
}

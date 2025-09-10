package com.example.totallearn.fragmentset;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.totallearn.R;
import com.example.totallearn.databinding.Fragment11Binding;
import com.example.totallearn.fragmentset.adapter.FragAdapter;

import java.util.ArrayList;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment11 extends Fragment {

    public static final String TAG = Fragment11.class.getSimpleName();

    private Fragment11Binding binding;

    private String[] titles = new String[]{"测试1","测试2","测试3"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FragAdapter fragAdapter;

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
        binding = Fragment11Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragments.add(new FragmentTab_01());
        fragments.add(new FragmentTab_02());
        fragments.add(new FragmentTab_03());
        binding.viewpager.setOffscreenPageLimit(2);
        for(int i=0;i<titles.length;i++){

            binding.tabLayout.addTab(binding.tabLayout.newTab());
        }

        binding.tabLayout.setupWithViewPager(binding.viewpager);

        fragAdapter = new FragAdapter(getChildFragmentManager(),fragments);
        binding.viewpager.setAdapter(fragAdapter);

        for(int i=0;i<titles.length;i++){
            binding.tabLayout.getTabAt(i).setText(titles[i]);
        }
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
        binding = null;
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
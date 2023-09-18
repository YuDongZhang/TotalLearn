package com.example.totallearn.fragmentset.frag05;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.example.totallearn.R;
import com.example.totallearn.databinding.Fragment05Binding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment05 extends BaseMVVMFragment<Fragment05Binding,CounterViewModel> {

    private List<String> list;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_05;
    }

    @Override
    protected CounterViewModel createViewModel() {
        return new ViewModelProvider(this).get(CounterViewModel.class);
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add("1,测试1");
        list.add("2,测试1");
        binding.buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.incrementCount();
            }
        });


        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        FiveRvAdapter adapter = new FiveRvAdapter(list);
        binding.recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new FiveRvAdapter.OnItemClickListener() {
            @Override
            public void onClick(String item, int position) {
                LogUtils.d(item+position);
            }
        });
    }

    @Override
    protected void observeViewModel() {
        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                binding.textViewCount.setText(String.valueOf(count));
            }
        });

    }

}

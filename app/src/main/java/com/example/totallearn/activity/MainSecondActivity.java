package com.example.totallearn.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.totallearn.R;
import com.example.totallearn.databinding.ActivityMain2Binding;
import com.example.totallearn.recyclerviewlearn.RClayoutmanager.MDGridRvDividerDecoration;
import com.example.totallearn.recyclerviewlearn.RClayoutmanager.RVAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainSecondActivity extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EventBus.getDefault().register(this);

        initViews();
    }

    @Subscribe
    public void event(String string) {
        Log.e("TAG", "event: " + string);
    }

    //
    @Subscribe(priority = 10, sticky = true)
    public void event2(String string) {
        Log.e("TAG", "event:2 " + string);
    }

    //粘性事件界面未初始化 , 或者延时都可以收到
    @Subscribe( sticky = true)
    public void event3(String string) {
        Log.e("TAG", "event:3 " + string);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void initViews() {
        ArrayList<String> mDataList = new ArrayList<>();
        addList(mDataList);
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 竖直方向的网格样式，每行四个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));

        binding.recyclerView.setAdapter(new RVAdapter(mDataList));
    }

    private void addList(ArrayList<String> arrayList) {
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8 ");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15 ");

    }

}
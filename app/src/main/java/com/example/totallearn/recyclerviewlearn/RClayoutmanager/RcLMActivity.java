package com.example.totallearn.recyclerviewlearn.RClayoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.totallearn.R;

import java.util.ArrayList;
import java.util.List;

public class RcLMActivity extends AppCompatActivity {

    private List<String> mDataList;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_lm);
        mRecyclerView = findViewById(R.id.rv_rclm);
        mDataList = new ArrayList<>();
        addList();
      //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 竖直方向的网格样式，每行四个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));

        mRecyclerView.setAdapter(new RVAdapter(mDataList));
    }

    private void addList(){
        mDataList.add("0");
        mDataList.add("1");
        mDataList.add("2");
        mDataList.add("3");
        mDataList.add("4");
        mDataList.add("5");
        mDataList.add("6");
        mDataList.add("7");
        mDataList.add("8 ");
        mDataList.add("9");
        mDataList.add("10");
        mDataList.add("11");
        mDataList.add("12");
        mDataList.add("13");
        mDataList.add("14");
        mDataList.add("15 ");

    }
}

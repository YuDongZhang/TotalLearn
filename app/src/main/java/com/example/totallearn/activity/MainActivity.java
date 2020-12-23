package com.example.totallearn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.totallearn.R;
import com.example.totallearn.recyclerviewlearn.RClayoutmanager.MDGridRvDividerDecoration;
import com.example.totallearn.recyclerviewlearn.RClayoutmanager.RVAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        ArrayList<String> mDataList = new ArrayList<>();
        addList(mDataList);
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 竖直方向的网格样式，每行四个Item
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));

        mRecyclerView.setAdapter(new RVAdapter(mDataList));
    }

    private void addList(ArrayList<String> arrayList){
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
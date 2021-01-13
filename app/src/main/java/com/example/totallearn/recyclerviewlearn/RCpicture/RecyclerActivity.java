package com.example.totallearn.recyclerviewlearn.RCpicture;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.totallearn.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        initData();

        initView();
    }

    public static String[] eatFoodyImages = {
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5837.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp",
            "http://img1.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p67.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46345.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399268395.47.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451727734.81.webp",
            "http://img1.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8833.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33896.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33301.webp",
            "http://img1.doubanio.com/view/photo/s_ratio_poster/public/p511118051.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28603.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1345.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p505.webp",
            "http://img3.doubanio.com/view/photo/s_ratio_poster/public/p510876377.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5837.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp",
            "http://img1.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p67.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46345.webp",

            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399268395.47.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451727734.81.webp",
            "http://img1.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8833.webp",
            "http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33896.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33301.webp",
            "http://img1.doubanio.com/view/photo/s_ratio_poster/public/p511118051.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28603.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1345.webp",
            "http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p505.webp",
            "http://img3.doubanio.com/view/photo/s_ratio_poster/public/p510876377.webp"

    };

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter(RecyclerActivity.this, getData());

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        for (String next : eatFoodyImages) {
//            MyLogUtil.i("RetrofitLog", "message = " + next);
            data.add(next);
        }
        return data;
    }


    private void initView() {
        mRecyclerView =  findViewById(R.id.recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);

    }


}

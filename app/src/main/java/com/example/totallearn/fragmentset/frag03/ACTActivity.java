package com.example.totallearn.fragmentset.frag03;

import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;



public class ACTActivity extends BaseActivity {

    private ImageView mIvBook;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private TextView mTvTitle;
    private TextView mTvRating;
    private TextView mTvMsg;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_c_t);
        
        // 初始化视图
        mIvBook = findViewById(R.id.iv_book_image);
        mToolbar = findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mTvTitle = findViewById(R.id.tv_title);
        mTvRating = findViewById(R.id.tv_rating);
        mTvMsg = findViewById(R.id.tv_msg);
        mTabLayout = findViewById(R.id.sliding_tabs);
        mViewpager = findViewById(R.id.viewpager);
        
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTabLayout.addTab(mTabLayout.newTab().setText("作者信息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("章节"));
        mTabLayout.addTab(mTabLayout.newTab().setText("书籍简介"));

        mIvBook.setImageResource(R.mipmap.ic_launcher);
        mCollapsingToolbarLayout.setTitle("我是书名");
        mTvTitle.setText("");
        mTvMsg.setText("作者" + "/" + "发行" + "/" + "2020");
        mTvRating.setText(50 + "分");
    }




}
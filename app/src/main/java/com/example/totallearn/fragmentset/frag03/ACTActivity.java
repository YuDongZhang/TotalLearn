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
import com.example.totallearn.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ACTActivity extends BaseActivity {

    @BindView(R.id.iv_book_image)
    ImageView mIvBook;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_rating)
    TextView mTvRating;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_c_t);
        ButterKnife.bind(this);
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
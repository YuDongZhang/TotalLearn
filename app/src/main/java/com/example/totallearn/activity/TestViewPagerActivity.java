package com.example.totallearn.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;
import com.example.totallearn.fragmentset.frag03.Fragment03;
import com.example.totallearn.fragmentset.frag01.Fragment01;
import com.example.totallearn.fragmentset.frag02.Fragment02;
import com.example.totallearn.fragmentset.frag05.Fragment05;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerActivity extends BaseActivity {

    private ViewPager viewpager;
    private List<Fragment> list;
    private boolean mIsChanged = false;
    private int mCurrentPagePosition = FIRST_ITEM_INDEX;
    private static final int POINT_LENGTH = 3;
    private static final int FIRST_ITEM_INDEX = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
        initData();
    }


    public void initView() {
        viewpager = findViewById(R.id.viewpager);
    }


    public void initData() {

        list = new ArrayList<>();
        list.add(new Fragment01());
        list.add(new Fragment02());
        list.add(new Fragment03());
        list.add(new Fragment01());
        list.add(new Fragment05());
        viewpager.setAdapter(new InviteFragmentAdapter(getSupportFragmentManager(), list));
        viewpager.setCurrentItem(1, false);
        viewpager.setOffscreenPageLimit(4);//记数从0开始!!! 设置预加载的个数
        viewpager.setPageTransformer(false, new DepthPageTransformer());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int pPosition) {
                mIsChanged = true;
                if (pPosition > POINT_LENGTH) {// 末位之后，跳转到首位（1）
                    mCurrentPagePosition = FIRST_ITEM_INDEX;
                } else if (pPosition < FIRST_ITEM_INDEX) {// 首位之前，跳转到末尾（N）
                    mCurrentPagePosition = POINT_LENGTH;
                } else {
                    mCurrentPagePosition = pPosition;
                }
            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }


            @Override
            public void onPageScrollStateChanged(int pState) {
                if (ViewPager.SCROLL_STATE_IDLE == pState) {
                    if (mIsChanged) {
                        mIsChanged = false;
                        viewpager.setCurrentItem(mCurrentPagePosition, false);
                    }
                }
            }
        });

    }


}

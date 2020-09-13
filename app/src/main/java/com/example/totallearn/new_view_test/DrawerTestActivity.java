package com.example.totallearn.new_view_test;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.totallearn.R;

public class DrawerTestActivity extends AppCompatActivity {
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;
    private RelativeLayout main_right_drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_test);
        initLayout();
        initEvent();

    }

    public void initLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //设置菜单内容之外其他区域的背景色
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        //右边菜单
        main_right_drawer_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);

    }

    //设置开关监听,设置布局偏移
    private void initEvent() {


        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, new Toolbar(this), R.string.open, R.string.close) {

            /*//可以做到一起布局一起移动效果 , 不使用布局就不一起动
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //可以重新侧滑方法,该方法实现侧滑动画,整个布局移动效果
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }*/

            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(drawerbar);
    }

    //左边菜单开关事件
//    public void openLeftLayout(View view) {
//        if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
//            drawerLayout.closeDrawer(main_left_drawer_layout);
//        } else {
//            drawerLayout.openDrawer(main_left_drawer_layout);
//        }
//    }
//
//    // 右边菜单开关事件
//    public void openRightLayout(View view) {
//        if (drawerLayout.isDrawerOpen(main_right_drawer_layout)) {
//            drawerLayout.closeDrawer(main_right_drawer_layout);
//        } else {
//            drawerLayout.openDrawer(main_right_drawer_layout);
//        }
//    }
}


package com.example.totallearn.new_view_test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.totallearn.R;
import com.example.totallearn.fragmentset.frag01.Fragment01;
import com.example.totallearn.fragmentset.frag02.Fragment02;
import com.example.totallearn.fragmentset.Fragment03;
import com.example.totallearn.fragmentset.frag04.Fragment04;

import java.util.ArrayList;
import java.util.List;

public class DrawerNavigationActivity extends AppCompatActivity {
    // 抽屉菜单对象
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;

    private TabFragmentPagerAdapter tabFragmentPagerAdapter;
    private List<Fragment> fragments;
    private MenuItem menuItem;
    private TextView tvHeader;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private MenuItem menuItem3;
    private MenuItem menuItem4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_navigation);
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navigationView = findViewById(R.id.navigationView);

        initFragments();
        initViewPager();

        initBottomNavigationView();
        initNavigationView();



    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment01());
        fragments.add(new Fragment02());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
    }

    private void initViewPager() {
        tabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(tabFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomNavigationView() {
       // BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//取消位移动画
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuItem = item;
                int i = item.getItemId();
                if (i == R.id.navigation_home) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (i == R.id.navigation_list) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (i == R.id.navigation_Three) {
                    viewPager.setCurrentItem(2);
                    return true;
                } else if (i == R.id.navigation_person) {
                    viewPager.setCurrentItem(3);
                    return true;
                }
                return false;
            }
        });
    }

    private void initNavigationView() {
        //登录
        tvHeader = navigationView.getHeaderView(0).findViewById(R.id.show_nav_head_text);
        menuItem1 = navigationView.getMenu().findItem(R.id.nav_item1);
        menuItem2 = navigationView.getMenu().findItem(R.id.nav_item2);
        menuItem3 = navigationView.getMenu().findItem(R.id.nav_item3);
        menuItem4 = navigationView.getMenu().findItem(R.id.nav_item4);
        //通过actionbardrawertoggle将toolbar与drawablelayout关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
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
            }
        };

        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        //设置图片为本身的颜色
        navigationView.setItemIconTintList(null);
        //设置item的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item == menuItem1) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item == menuItem2) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item == menuItem3) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item == menuItem4) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
        //头部设置监听
        tvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawerNavigationActivity.this, "登录成功", Toast.LENGTH_LONG).show();
            }
        });
    }


    private class TabFragmentPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragmentList;
        public TabFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragmentList1) {
            super(fm);
            this.fragmentList = fragmentList1;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

   /* private class TabFragmentPagerAdapter extends PagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {

        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(fragments.get(position));
        }
    }*/


}


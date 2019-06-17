package com.example.totallearn;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.totallearn.fragmentset.Fragment01;
import com.example.totallearn.fragmentset.Fragment02;
import com.example.totallearn.fragmentset.Fragment03;
import com.example.totallearn.fragmentset.Fragment04;
import com.example.totallearn.fragmentset.Fragment05;
import com.example.totallearn.fragmentset.Fragment06;
import com.example.totallearn.fragmentset.Fragment07;
import com.example.totallearn.fragmentset.Fragment08;
import com.example.totallearn.fragmentset.Fragment09;
import com.example.totallearn.serviceset.TestService;

/**
 * 在个人电脑上进行同步
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private Button bt8;
    private Button bt9;

    private Fragment01 mFragment01;
    private Fragment02 mFragment02;
    private Fragment03 mFragment03;
    private Fragment04 mFragment04;
    private Fragment05 mFragment05;
    private Fragment06 mFragment06;
    private Fragment07 mFragment07;
    private Fragment08 mFragment08;
    private Fragment09 mFragment09;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test();
        initView();

        showFragment(2);//预加载第一页

    }

    private void test() {
        Intent intent = new Intent(this,SwipeScrollRecyclerActivity.class);
        startActivity(intent);
    }

    private void initView() {
        bt1 = findViewById(R.id.bt_1);
        bt2 = findViewById(R.id.bt_2);
        bt3 = findViewById(R.id.bt_3);
        bt4 = findViewById(R.id.bt_4);
        bt5 = findViewById(R.id.bt_5);
        bt6 = findViewById(R.id.bt_6);
        bt7 = findViewById(R.id.bt_7);
        bt8 = findViewById(R.id.bt_8);
        bt9 = findViewById(R.id.bt_9);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                showFragment(1);
                break;
            case R.id.bt_2:
                showFragment(2);
                break;
            case R.id.bt_3:
                showFragment(3);
                break;
            case R.id.bt_4:
                showFragment(4);
                break;
            case R.id.bt_5:
                showFragment(5);
                break;
            case R.id.bt_6:
                showFragment(6);
                break;
            case R.id.bt_7:
                showFragment(7);
                break;
            case R.id.bt_8:
                showFragment(8);
                break;
            case R.id.bt_9:
                showFragment(9);//暂时用做测试
                break;
        }
    }

    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 1:
                if (mFragment01 == null) {
                    mFragment01 = new Fragment01();
                    ft.add(R.id.container, mFragment01);
                } else {
                    ft.show(mFragment01);
                }
                break;

            case 2:
                if (mFragment02 == null) {
                    mFragment02 = new Fragment02();
                    ft.add(R.id.container, mFragment02);
                } else {
                    ft.show(mFragment02);
                }
                break;
            case 3:
                if (mFragment03 == null) {
                    mFragment03 = new Fragment03();
                    ft.add(R.id.container, mFragment03);
                } else {
                    ft.show(mFragment03);
                }
                break;
            case 4:
                if (mFragment04 == null) {
                    mFragment04 = new Fragment04();
                    ft.add(R.id.container, mFragment04);
                } else {
                    ft.show(mFragment04);
                }
                break;
            case 5:
                if (mFragment05 == null) {
                    mFragment05 = new Fragment05();
                    ft.add(R.id.container, mFragment05);
                } else {
                    ft.show(mFragment05);
                }
                break;
            case 6:
                if (mFragment06 == null) {
                    mFragment06 = new Fragment06();
                    ft.add(R.id.container, mFragment06);
                } else {
                    ft.show(mFragment06);
                }
                break;
            case 7:
                if (mFragment07 == null) {
                    mFragment07 = new Fragment07();
                    ft.add(R.id.container, mFragment07);
                } else {
                    ft.show(mFragment07);
                }
                break;
            case 8:
                if (mFragment08 == null) {
                    mFragment08 = new Fragment08();
                    ft.add(R.id.container, mFragment08);
                } else {
                    ft.show(mFragment08);
                }
                break;
            case 9:
                if (mFragment09 == null) {
                    mFragment09 = new Fragment09();
                    ft.add(R.id.container, mFragment09);
                } else {
                    ft.show(mFragment09);
                }
                break;

        }
        ft.commitAllowingStateLoss();
    }

    public void hideFragment(FragmentTransaction ft) {
        if (mFragment01 != null) {
            ft.hide(mFragment01);
        }
        if (mFragment02 != null) {
            ft.hide(mFragment02);
        }
        if (mFragment03 != null) {
            ft.hide(mFragment03);
        }
        if (mFragment04 != null) {
            ft.hide(mFragment04);
        }
        if (mFragment05 != null) {
            ft.hide(mFragment05);
        }
        if (mFragment06 != null) {
            ft.hide(mFragment06);
        }
        if (mFragment07 != null) {
            ft.hide(mFragment07);
        }
        if (mFragment08 != null) {
            ft.hide(mFragment08);
        }
        if (mFragment09 != null) {
            ft.hide(mFragment09);
        }


    }
}

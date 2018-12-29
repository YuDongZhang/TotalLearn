package com.example.totallearn;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.totallearn.fragmentset.Fragment01;
import com.example.totallearn.fragmentset.Fragment02;
import com.example.totallearn.fragmentset.Fragment03;
import com.example.totallearn.fragmentset.Fragment04;
import com.example.totallearn.fragmentset.Fragment05;

/**
 * 在个人电脑上进行同步
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;

    private Fragment01 mFragment01;
    private Fragment02 mFragment02;
    private Fragment03 mFragment03;
    private Fragment04 mFragment04;
    private Fragment05 mFragment05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        bt1 = findViewById(R.id.bt_1);
        bt2 = findViewById(R.id.bt_2);
        bt3 = findViewById(R.id.bt_3);
        bt4 = findViewById(R.id.bt_4);
        bt5 = findViewById(R.id.bt_5);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
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
    }
}

package com.example.totallearn;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ToastUtils;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.fragmentset.frag08.Fragment08;
import com.example.totallearn.fragmentset.frag09.Fragment09;
import com.example.totallearn.fragmentset.Fragment10;
import com.example.totallearn.fragmentset.Fragment11;
import com.example.totallearn.fragmentset.frag01.Fragment01;
import com.example.totallearn.fragmentset.frag02.Fragment02;
import com.example.totallearn.fragmentset.frag03.Fragment03;
import com.example.totallearn.fragmentset.frag04.Fragment04;
import com.example.totallearn.fragmentset.frag05.Fragment05;
import com.example.totallearn.fragmentset.frag06.Fragment06;
import com.example.totallearn.fragmentset.frag07.Fragment07;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 在个人电脑上进行同步
 */
public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();


    private Fragment01 mFragment01;
    private Fragment02 mFragment02;
    private Fragment03 mFragment03;
    private Fragment04 mFragment04;
    private Fragment05 mFragment05;
    private Fragment06 mFragment06;
    private Fragment07 mFragment07;
    private Fragment08 mFragment08;
    private Fragment09 mFragment09;
    private Fragment10 mFragment10;
    private Fragment11 mFragment11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //test();

        showFragment(1);//预加载第一页

        requestPermissions();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() { //observer 也可以,代码多一点
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            ToastUtils.showShort("权限开启");
                        } else {
                            ToastUtils.showShort("去权限设置打开权限,方便使用");
                        }
                    }
                });
    }


    private void test() {
        Intent intent = new Intent(this, SwipeScrollRecyclerActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4, R.id.bt_5, R.id.bt_6, R.id.bt_7, R.id.bt_8,
            R.id.bt_9,R.id.bt_10,R.id.bt_11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                showFragment(9);
                break;
            case R.id.bt_10:
                showFragment(10);
                break;
            case R.id.bt_11:
                showFragment(11);
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
            case 10:
                if (mFragment10 == null) {
                    mFragment10 = new Fragment10();
                    ft.add(R.id.container, mFragment10);
                } else {
                    ft.show(mFragment10);
                }
                break;
            case 11:
                if (mFragment11 == null) {
                    mFragment11 = new Fragment11();
                    ft.add(R.id.container, mFragment11);
                } else {
                    ft.show(mFragment11);
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
        if (mFragment10 != null) {
            ft.hide(mFragment10);
        }
        if (mFragment11 != null) {
            ft.hide(mFragment11);
        }


    }

}

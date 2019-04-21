package com.example.totallearn.mvp_set.mvp2.impl;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.totallearn.mvp_set.mvp2.MvpPresenter;
import com.example.totallearn.mvp_set.mvp2.MvpView;

/**
 * Created by Shinelon on 2019/2/23.
 */
//具体同事A (抽象) -> 绑定 V 层 , 解绑 V 层
public abstract class MvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends Activity implements MvpView {
    private P presenter;

    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (this.presenter == null){
            this.presenter = createPresenter();
        }
        if (this.presenter == null){
           throw new NullPointerException("P层不能为空");
        }
    }

    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}

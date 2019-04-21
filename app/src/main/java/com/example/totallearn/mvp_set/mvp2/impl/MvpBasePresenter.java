package com.example.totallearn.mvp_set.mvp2.impl;

import com.example.totallearn.mvp_set.mvp2.MvpPresenter;
import com.example.totallearn.mvp_set.mvp2.MvpView;

/**
 * Created by Shinelon on 2019/2/23.
 */

//实现具体的绑定
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    //这里可以采用弱引用
    private V view;

    public V getView(){
        return view;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}

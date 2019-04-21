package com.example.totallearn.mvp_set.mvp2;

/**
 * Created by Shinelon on 2019/2/23.
 */
//抽象中介者
    //更多的暴露接口
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}

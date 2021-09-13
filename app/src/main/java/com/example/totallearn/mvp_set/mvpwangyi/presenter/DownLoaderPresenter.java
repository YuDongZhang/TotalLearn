package com.example.totallearn.mvp_set.mvpwangyi.presenter;

import android.os.SystemClock;

import com.example.totallearn.mvp_set.mvpwangyi.DownloaderContract;
import com.example.totallearn.mvp_set.mvpwangyi.WYmvpActivity;
import com.example.totallearn.mvp_set.mvpwangyi.engine.DownLoaderEngine;
import com.example.totallearn.mvp_set.mvpwangyi.model.ImageBean;

// P层几乎不做事情？谷歌的sample中，P层是包揽了所有的活
public class DownLoaderPresenter implements DownloaderContract.PV {

    private WYmvpActivity view;
    private DownLoaderEngine model; // 下载的模型

    public DownLoaderPresenter(WYmvpActivity view) {
        this.view = view;
        model = new DownLoaderEngine(this);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        // 接收到View层的指令，去完成某个需求（可以自己完成，也可以让别人去完成）
        try {
            model.requestDownloader(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
            // 省略了异常的处理
        }

        //内存泄漏测试 ,可以把上面的注销掉测试这个,
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(50000);
//            }
//        }).start();
    }

    @Override
    public void responseDownloaderResult(final boolean isSuccess, final ImageBean imageBean) {
        // 将完成的结果告知View层（刷新UI）
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.responseDownloaderResult(isSuccess, imageBean);
            }
        });
    }
}

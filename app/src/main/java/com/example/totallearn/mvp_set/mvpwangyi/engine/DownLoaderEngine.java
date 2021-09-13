package com.example.totallearn.mvp_set.mvpwangyi.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.totallearn.mvp_set.mvpwangyi.DownloaderContract;
import com.example.totallearn.mvp_set.mvpwangyi.model.ImageBean;
import com.example.totallearn.mvp_set.mvpwangyi.presenter.DownLoaderPresenter;
import com.example.totallearn.mvp_set.mvpwangyi.utils.Constant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoaderEngine implements DownloaderContract.M {

    private DownLoaderPresenter presenter;

    public DownLoaderEngine(DownLoaderPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestDownloader(ImageBean imageBean) throws Exception {
        // P层让我做这个需求
        new Thread(new DownLoader(imageBean)).start();
    }

    final class DownLoader implements Runnable {

        private final ImageBean imageBean;

        public DownLoader(ImageBean imageBean) {
            this.imageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageBean.getRequestPath());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(Constant.SUCCESS, bitmap);
                } else {
                    showUi(Constant.ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(Constant.ERROR, null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            imageBean.setBitmap(bitmap);
            presenter.responseDownloaderResult(resultCode == Constant.SUCCESS, imageBean);
        }
    }
}

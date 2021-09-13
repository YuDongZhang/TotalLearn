package com.example.totallearn.mvp_set.mvpwangyi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.totallearn.R;
import com.example.totallearn.mvp_set.mvpwangyi.model.ImageBean;
import com.example.totallearn.mvp_set.mvpwangyi.presenter.DownLoaderPresenter;
import com.example.totallearn.mvp_set.mvpwangyi.utils.Constant;


// MVC中Activity是C层，MVP中Activity是V层
public class WYmvpActivity extends AppCompatActivity implements DownloaderContract.PV {

    private ImageView imageView;
    private DownLoaderPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wy_mvp);

        imageView = findViewById(R.id.iv);
        presenter = new DownLoaderPresenter(this);
    }

    // 点击事件
    public void down(View view) {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(Constant.IMAGE_PATH);
        requestDownloader(imageBean);
    }

    @Override
    public void requestDownloader(ImageBean imageBean) {
        if (presenter != null) presenter.requestDownloader(imageBean);
    }

    @Override
    public void responseDownloaderResult(boolean isSuccess, ImageBean imageBean) {
        Toast.makeText(this, isSuccess ? "下载成功" : "下载失败", Toast.LENGTH_SHORT).show();
        if (isSuccess && imageBean.getBitmap() != null) {
            imageView.setImageBitmap(imageBean.getBitmap());
        }
    }
}

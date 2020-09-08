package com.example.totallearn.fragmentset.frag04.retrofit;

import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.utils.MyLogUtil;

import java.io.File;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class RetrofitLearnActivity extends BaseActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.et_url)
    EditText etUrl;
    private Disposable mDownloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrifit_learn);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_down)
    public void onViewClicked() {
        downloadFile("https://app.njpdxx.com/ios/res_yun_chaozhou_shixi/res_yun_chaozhou_shixi_android_1.0.27_release.apk");
    }

    private void downloadFile(String url) {
        String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "111.apk";
        RxNet.download(url, path, new DownloadCallback() {
            @Override
            public void onStart(Disposable d) {
                mDownloadTask = d;
                MyLogUtil.d(TAG,"onStart " + d);
                Toast.makeText(RetrofitLearnActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(long totalByte, long currentByte, int progress) {
                MyLogUtil.d(TAG,"onProgress " + progress);
                progressBar.setProgress(progress);
               /* tvProgress.setText(progress + "%");
                tvTotalM.setText(byteFormat(totalByte));
                tvDownloadM.setText(byteFormat(currentByte));*/
            }

            @Override
            public void onFinish(File file) {
                MyLogUtil.d(TAG,"onFinish " + file.getAbsolutePath());
                Toast.makeText(RetrofitLearnActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String msg) {
                MyLogUtil.d(TAG,"onError " + msg);
            }
        });
    }

    private String byteFormat(long bytes) {
        BigDecimal fileSize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = fileSize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1) {
            return (returnValue + "MB");
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fileSize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return (returnValue + "KB");
    }
}
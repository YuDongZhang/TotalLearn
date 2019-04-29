package com.example.totallearn;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

public class PdfActivity extends AppCompatActivity {

    @BindView(R.id.pdfview)
    PDFView pdfview;

    private String mPdfUrl = "http://pdf.dfcfw.com/pdf/H2_AN201807051163584888_1.pdf";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static Activity mCtx;
    private String cacheUrl = "";//应用缓存路径
    private String pdfName = "error";//文件名称   默认一个错误名称
    private boolean mLoadComplete = false;//加载完成
    private TextView mPageTv;//页数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);
        mCtx = this;
        mPageTv = findViewById(R.id.pdfpage);
        checkPerMission();
    }

    private Handler handler = null;
    private Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            checkPerMission();
        }
    };

    /**
     * 检查读写权限
     */
    private void checkPerMission() {
        int permission = ActivityCompat.checkSelfPermission(mCtx,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {//无权限  申请
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(mCtx, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
            if (handler == null) {
                handler = new Handler();
            }
            handler.postDelayed(checkRunnable, 1000);//隔一秒再检查
        } else {
            DownloadPdf();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {//删除文件  避免引起缓存过大
            File dest = new File(cacheUrl, pdfName);
            if (dest.exists()) {
                dest.delete();
            }
        } catch (Exception e) {

        }
    }

    /**
     * 开始下载PDF
     */
    private void DownloadPdf() {
        cacheUrl = getCacheDir().getAbsolutePath();//应用缓存路径
        pdfName = mPdfUrl.substring(mPdfUrl.lastIndexOf("/") + 1);//文件名称
        final File dest = new File(cacheUrl, pdfName);
        if (dest.exists()) {
            SeePdf(dest);
        } else {
            Request request = new Request.Builder().url(mPdfUrl).build();
            new OkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // 下载失败
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Sink sink = null;
                    BufferedSink bufferedSink = null;
                    try {
                        if (!dest.exists()) {
                            boolean newFile = dest.createNewFile();
                        }
                        sink = Okio.sink(dest);
                        bufferedSink = Okio.buffer(sink);
                        bufferedSink.writeAll(response.body().source());
                        bufferedSink.close();
                        if (handler == null) {
                            handler = new Handler(Looper.getMainLooper());
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                SeePdf(dest);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bufferedSink != null) {
                            bufferedSink.close();
                        }

                    }
                }
            });
        }
    }

    /**
     * 查看PDF
     */
    private void SeePdf(File dest) {
        try {

            pdfview.setVisibility(View.VISIBLE);
            pdfview.fromFile(dest)
                    .defaultPage(1)  //设置默认显示第1页
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            if (mPageTv != null && page > 0 && pageCount >= page) {
                                mPageTv.setText(page + "/" + pageCount);
                            }
                        }
                    }) //设置翻页监听
                    .onDraw(new OnDrawListener() {
                        @Override
                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                            refreshPageView();
                        }
                    })
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            mLoadComplete = true;
                        }
                    })

                    // .showMinimap(false) //pdf放大的时候，是否在屏幕的右上角生成小地图
                    // .swipeVertical(true) //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                    .onPageScroll(new OnPageScrollListener() {
                        @Override
                        public void onPageScrolled(int page, float positionOffset) {
                            LogUtils.d(page);
                            LogUtils.d(positionOffset);
                        }
                    })
                    .enableSwipe(true) //是否允许翻页，默认是允许翻页
                    .load();


            pdfview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {

                }
            });
            //https://www.cnblogs.com/lonelyxmas/p/9855754.html
            pdfview.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    switch (keyEvent.getKeyCode()){
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            break;
                        case KeyEvent.ACTION_UP:
                            break;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏页数
     */
    Runnable hidePage = new Runnable() {
        @Override
        public void run() {
            if (mPageTv != null) {
                mPageTv.setVisibility(View.GONE);
            }
        }
    };

    private void refreshPageView() {
        if (mPageTv != null && !mPageTv.isShown()) {
            mPageTv.setVisibility(View.VISIBLE);
        }
        if (handler == null) {
            handler = new Handler();
        }
        handler.removeCallbacks(hidePage);
        handler.postDelayed(hidePage, 3000);
    }
    int y = -1000;
    @OnClick(R.id.button)
    public void onViewClicked() {


        y = y-1000;
        pdfview.moveTo(0,y);
    }
}

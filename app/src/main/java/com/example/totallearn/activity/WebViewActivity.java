package com.example.totallearn.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private WebView webView;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private PermissionRequest pendingPermissionRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initViews();
        initPermissions();
        loadLocalHtml();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initViews() {
        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                runOnUiThread(() -> {
                    if (ContextCompat.checkSelfPermission(WebViewActivity.this, 
                            Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        request.grant(request.getResources());
                    } else {
                        pendingPermissionRequest = request;
                        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
                    }
                });
            }

            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                super.onPermissionRequestCanceled(request);
                Toast.makeText(WebViewActivity.this, "录音权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initPermissions() {
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        Toast.makeText(this, "录音权限已获取", Toast.LENGTH_SHORT).show();
                        if (pendingPermissionRequest != null) {
                            pendingPermissionRequest.grant(pendingPermissionRequest.getResources());
                            pendingPermissionRequest = null;
                        }
                    } else {
                        Toast.makeText(this, "录音权限被拒绝", Toast.LENGTH_SHORT).show();
                        if (pendingPermissionRequest != null) {
                            pendingPermissionRequest.deny();
                            pendingPermissionRequest = null;
                        }
                    }
                }
        );
    }

    private void loadLocalHtml() {
        webView.loadUrl("file:///android_asset/recording.html");
    }
}

/**
 * 
 * 明白了！现在的实现已经可以测试 WebView 的动态权限请求了。

**当前实现的权限流程：**

1. **点击录音按钮** → JavaScript 调用 `navigator.mediaDevices.getUserMedia({ audio: true })`

2. **WebView 触发权限请求** → `WebChromeClient.onPermissionRequest()` 被调用

3. **Android 处理权限**：
   - 检查是否已有 `RECORD_AUDIO` 权限
   - 如果没有，使用 `ActivityResultLauncher` 请求权限
   - 系统弹出权限对话框

4. **用户选择授权/拒绝** → 回调处理结果：
   - 授权：调用 `pendingPermissionRequest.grant()`，录音开始
   - 拒绝：调用 `pendingPermissionRequest.deny()`，显示提示

**关键代码位置：**
- [WebViewActivity.java#L50-L70](file:///c:/projects/android/TotalLearn/app/src/main/java/com/example/totallearn/activity/WebViewActivity.java#L50-L70) - `onPermissionRequest()` 处理
- [WebViewActivity.java#L75-L90](file:///c:/projects/android/TotalLearn/app/src/main/java/com/example/totallearn/activity/WebViewActivity.java#L75-L90) - `initPermissions()` 设置

现在可以运行应用，点击"开始录音"按钮，应该能看到系统弹出的权限请求对话框。
 * 
 * 
 * 
 * 
 */

package com.example.totallearn.fragmentset.frag04.retrofit;

import okhttp3.ResponseBody;

/**
 * @author LvQiSheng
 * @date 2019/7/19
 */
public interface DownloadListener {
    void onStart(ResponseBody responseBody);
}

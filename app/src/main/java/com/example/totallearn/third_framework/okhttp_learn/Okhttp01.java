package com.example.totallearn.third_framework.okhttp_learn;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp01 {

    public Okhttp01() {
    }

    OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
    public void synRequest(){
        //设置请求 head ，url
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();
        //一个实际的请求  ，request 和 response 桥梁
        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            LogUtils.d("adb",response.body().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

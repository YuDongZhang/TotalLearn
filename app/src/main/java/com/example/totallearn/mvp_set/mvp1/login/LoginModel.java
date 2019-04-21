package com.example.totallearn.mvp_set.mvp1.login;



import com.example.totallearn.mvp_set.demo1.utills.HttpTask;
import com.example.totallearn.mvp_set.demo1.utills.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: Dream on 2018/3/9 21:15
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//M层->具体同事A
public class LoginModel  {

    public void login(String username, String password, final HttpUtils.OnHttpResultListener onHttpResultListener){
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        new HttpTask.Builder()
                .method("POST")
                .url("")//注意url
                .paramMap(params)
                .onHttpResultListener(new HttpUtils.OnHttpResultListener() {
                    @Override
                    public void onResult(String result) {
                        onHttpResultListener.onResult(result);
                    }
                }).build().execute();
    }

}

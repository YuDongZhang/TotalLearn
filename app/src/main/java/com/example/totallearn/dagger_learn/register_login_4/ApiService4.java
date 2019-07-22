package com.example.totallearn.dagger_learn.register_login_4;

import android.content.Context;
import android.util.Log;

import com.example.totallearn.utils.LogUtil;

/**
 * 两个构造方法   参数不同  , 可以查看 usermode 中怎么来进行创建的
 */
public class ApiService4 {
    public static final String TAG = ApiService4.class.getSimpleName();

    public ApiService4(Context context){

    }

    public ApiService4(String url){
        LogUtil.d(TAG, "ApiService4 "+url);
    }



    public void register4() {
        //注册的方法
         Log.i("TAG", "ApiService: ");
    }

}
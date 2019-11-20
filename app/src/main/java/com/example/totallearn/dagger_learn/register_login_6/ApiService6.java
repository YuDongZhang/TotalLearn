package com.example.totallearn.dagger_learn.register_login_6;

import android.content.Context;
import android.util.Log;

import com.example.totallearn.utils.MyLogUtil;

/**
 * 两个构造方法   参数不同  , 可以查看 usermode 中怎么来进行创建的
 */
public class ApiService6 {
    public static final String TAG = ApiService6.class.getSimpleName();

    public ApiService6(Context context){

    }

    public ApiService6(String url){
        MyLogUtil.d(TAG, "ApiService4 "+url);
    }



    public void register4() {
        //注册的方法
         Log.i("TAG", "ApiService: ");
    }

}
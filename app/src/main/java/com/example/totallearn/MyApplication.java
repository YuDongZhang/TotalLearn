package com.example.totallearn;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.totallearn.dagger_learn.register_login_6.AppComponent;
import com.example.totallearn.dagger_learn.register_login_6.AppMoudle;
import com.example.totallearn.dagger_learn.register_login_6.DaggerAppComponent;
import com.example.totallearn.dagger_learn.register_login_7.DaggerFComponent;
import com.example.totallearn.dagger_learn.register_login_7.FComponent;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;


/**
 * 3. 在MyApplication实例化AppComponent：
 */
public class MyApplication extends Application {

    private AppComponent mAppComponent;

    //测试 7 和 包7
    private FComponent fComponent;

    private OkHttpClient okHttpClient;

    public MyApplication() {
        Utils.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //在写的时候看到  包 4中也有一个 DaggerAppComponent, 这个应该是遗留下来的 , 现在用的 是包 6中
        mAppComponent = DaggerAppComponent.builder().appMoudle(new AppMoudle(this)).build();

        fComponent = DaggerFComponent.builder().build();

        initOkgo();
    }

    private void initOkgo() {
//配置okgo
        OkGo.getInstance().init(this)
                //必须调用初始化
                .setOkHttpClient(getOkHttpClient())//建议设置OkHttpClient，不设置将使用默认的
                .setRetryCount(3);
    }

    public OkHttpClient getOkHttpClient() {
        if(okHttpClient==null){
            //构建okhttp.Builder（）
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //配置log
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OKGo");
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            loggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(loggingInterceptor);
            //配置超时时间
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public FComponent getFComponent() {
        return fComponent;
    }
}

package com.example.totallearn.fragmentset.frag04;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends BaseActivity {
    //https://www.jianshu.com/p/10382cc71127
    //https://blog.csdn.net/Vazzz/article/details/84099982
    //https://www.jianshu.com/p/9e7937e174ab
    //用法记忆 : OkHttpClient  Request  call  客户请求(请求实体)呼叫
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.oh_get, R.id.oh_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.oh_get:
                okhttpGet();
                break;

            case R.id.oh_post:
                okhttpPost();
                break;
        }
    }

    private void okhttpGet() {
        //第一步获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder().build();

        //第二步构建Request对象
        Request request = new Request.Builder()
                .url("https://m.baidu.com")
                .get()
                .build();

        //第三步构建Call对象
        final Call call = client.newCall(request);

        //第四步:异步get请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.i("onFailure", e.getMessage());
                ToastUtils.showShort("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ToastUtils.showShort("请求成功");
                String result = response.body().string();
                // LogUtils.i("result", result);
                LogUtils.xml(result);
            }
        });

        //第四步 同步请求  要在子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //call.execute();//同步请求
                    Response response = call.execute();
                    LogUtils.d(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //这个是他的post请求
    private void okhttpPost() {//如果再pdxx测试要打开wifi
        //第一步创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //第二步创建RequestBody（Form表达）
        RequestBody body = new FormBody.Builder()
                .add("lectureFlow", "eea5f95d0d524b73859fb629750d52eb")
                .build();

        //第三步创建Rquest
        Request request = new Request.Builder()
                .url("http://192.168.2.96:9090/pdapp/res/gydxj/admin/getLecture")
                .post(body)
                .build();

        //第四步创建call回调对象
        final Call call = client.newCall(request);

        // call.cancel(); 取消

        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.i("onFailure", e.getMessage());
                ToastUtils.showShort("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ToastUtils.showShort("请求成功");
                String result = response.body().string();
                LogUtils.d("result", result);
            }
        });
    }






    /*
     OkHttpClient -->Request -->Call call = client.newCall(request);
     -->Response response = call.execute()
     -->call.enqueue(new Callback()
     */


    public void rxjavaTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /*
    其实本来 是  Observable.subscribe(Observer),不过是被观察者需要操作符，
    subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。
    observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。
    Scheduler，相当于线程调度器，读：思改雀了
    原理：

    subscribeOn()
    Schedulers.io()生成的一个线程调度对象,此对象是维护这一个线程池,让操作在io线程池中执行
    observeOn()
    根据 mainThread() 源码的调用关系来看，最终返回的是 HandlerScheduler 对象，HandlerScheduler
    是一个 Scheduler 的子类，其内部封装了一个可以在主线程发送消息的 handler 对象。看到这里就大概明白了，
    将 observer 切换到主线程去接收事件，内部就是通过一个可以在主线程发送消息的 Handler 去实现的。



     */

}

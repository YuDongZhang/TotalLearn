package com.example.totallearn.okhttplearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }

    OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    public void synRequest() {
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            System.out.print(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void aynResquest(){
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //这个两个方法在工作线程
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print(response.body().string());
            }
        });
    }
    /*
     OkHttpClient -->Request -->Call call = client.newCall(request);
     -->Response response = call.execute()
     -->call.enqueue(new Callback()
     */


    public void rxjavaTest(){
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

package com.example.totallearn.fragmentset.frag04;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RxjavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
    }

    private void test1(){//记忆 observable  subscribe  observer
        Observable.create(new ObservableOnSubscribe<Integer>() { //第一步：初始化Observable
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n" );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {// 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n" );
            }
        });
    }

    @SuppressLint("CheckResult")
    private void test2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(io)，Current thread is " + Thread.currentThread().getName());
                    }
                });
    }

    private void test3(){
            Observable.create(new ObservableOnSubscribe<Response>() {
                @Override
                public void subscribe(ObservableEmitter<Response> e) throws Exception {
                    OkHttpClient client = new OkHttpClient.Builder().build();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .get()
                            .build();

                    Call call = client.newCall(request);
                    Response response = call.execute();
                    e.onNext(response);
                }
            }).map();
    }
}

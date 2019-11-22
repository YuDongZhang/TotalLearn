package com.example.totallearn.fragmentset.frag04;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.fragmentset.frag04.f4entity.JokeEntity;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * observeable subscribe observer 不支持背压
 *
 * flowable  subscribe subscribe 支持背压(告诉上游降低发送速度)  响应式编程是一种基于异步数据流概念的编程模式。
 * 数据流就像一条河：它可以被观测，被过滤，被操作，或者为新的消费者与另外一条流合并为一条新的流。
 */

public class RxjavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

    }

    private void test1(){//记忆 observable  subscribe  observer
        Observable.create(new ObservableOnSubscribe<Integer>() { //第一步：初始化Observable
            @Override //，回调的是 ObservableEmitter  作者在文章中称呼这个为回调
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

            // 我们的 RxJava 2.x 也为我们保留了简化订阅方法，我们可以根据需求，进行相应的简化订阅，只不过传入对象改为了 Consumer。
            //Consumer 即消费者，用于接收单个值，BiConsumer 则是接收两个值，Function 用于变换对象，Predicate 用于判断。
            // 这些接口命名大多参照了 Java 8 ，熟悉 Java 8 新特性的应该都知道意思，这里也不再赘述。



            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;//Disposable    用于解除订阅
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
                //subscribeOn 用于指定 subscribe() 时所发生的线程，从源码角度可以看出，内部线程调度是通过 ObservableSubscribeOn来实现的。
                 //ObservableSubscribeOn 的核心源码在 subscribeActual 方法中，通过代理的方式使用 SubscribeOnObserver 包装 Observer 后，
                // 设置 Disposable 来将 subscribe 切换到 Scheduler 线程中。




                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //observeOn 方法用于指定下游 Observer 回调发生的线程。


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


    }/*简单地说，subscribeOn() 指定的就是发射事件的线程，observerOn 指定的就是订阅者接收事件的线程。
        多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。
        但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。

        实例代码中，分别用 Schedulers.newThread() 和 Schedulers.io() 对发射线程进行切换，并采用
         observeOn(AndroidSchedulers.mainThread() 和 Schedulers.io() 进行了接收线程的切换。可以看到输出中
         发射线程仅仅响应了第一个 newThread，但每调用一次 observeOn() ，线程便会切换一次，因此如果我们有类似的需求时，便知道如何处理了。

        RxJava 中，已经内置了很多线程选项供我们选择，例如有：

        Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作；
        Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作；
        Schedulers.newThread() 代表一个常规的新线程；
        AndroidSchedulers.mainThread() 代表Android的主线程

        作者：nanchen2251
        链接：https://www.jianshu.com/p/0cd258eecf60
        来源：简书
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */




    //map 可以用于数据的多次的操作 , 比如你要先解析 , 再存储 , 再展示等等
    private void test3(){
            Observable.create(new ObservableOnSubscribe<Response>() {
                @Override
                public void subscribe(ObservableEmitter<Response> e) throws Exception {
                    OkHttpClient client = new OkHttpClient.Builder().build();
                    Request request = new Request.Builder()
                            .url("https://api.apiopen.top/getJoke")
                            .get()
                            .build();

                    Call call = client.newCall(request);
                    Response response = call.execute();
                    e.onNext(response);
                }
            }).map(new Function<Response, JokeEntity>() {
                @Override
                public JokeEntity apply(Response response) throws Exception {
                        if (response.isSuccessful()){
                            ResponseBody body = response.body();
                            if(body != null){
                                Log.e(TAG, "map:转换前:" + response.body());
                                return new Gson().fromJson(body.string(), JokeEntity.class);
                            }

                        }

                    return null;
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<JokeEntity>() {
                        @Override
                        public void accept(JokeEntity jokeEntity) throws Exception {
                            Log.e(TAG, "doOnNext: 保存成功：" + jokeEntity.toString() + "\n");
                        }
                    }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<JokeEntity>() {
                        @Override
                        public void accept(JokeEntity jokeEntity) throws Exception {
                            Log.e(TAG, "成功:" + jokeEntity.toString() + "\n");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                        }
                    });

            /*
            想必大家都知道，很多时候我们在使用 RxJava 的时候总是和 Retrofit 进行结合使用，而为了方便演示，这里我们就暂且采用 OkHttp3
            进行演示，配合 map，doOnNext ，线程切换进行简单的网络请求：
            1）通过 Observable.create() 方法，调用 OkHttp 网络请求；
            2）通过 map 操作符集合 gson，将 Response 转换为 bean 类；
            3）通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作；
            4）调度线程，在子线程中进行耗时操作任务，在主线程中更新 UI ；
            5）通过 subscribe()，根据请求成功或者失败来更新 UI 。

            作者：nanchen2251
            链接：https://www.jianshu.com/p/0cd258eecf60
            来源：简书
            著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
             */
    }



    private void test4(){
        Observable<JokeEntity> cache = Observable.create(new ObservableOnSubscribe<JokeEntity>() {
            @Override
            public void subscribe(ObservableEmitter<JokeEntity> emitter) throws Exception {
                Log.e(TAG, "create当前线程:"+Thread.currentThread().getName() );
              //  JokeEntity data  =
            }
        })
    }




}

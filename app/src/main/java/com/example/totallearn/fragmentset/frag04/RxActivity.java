package com.example.totallearn.fragmentset.frag04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.totallearn.R;
import com.example.totallearn.fragmentset.adapter.Frag09Adapter;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity10;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity11;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity2;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity3;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity4;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity5;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity6;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity7;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity8;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.MainActivity9;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.example.totallearn.fragmentset.frag04.Fragment04.TAG;

public class RxActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String[] data = {
            "测试",
            "1. Rxjava和Rtofit结合",
            "rxjava上游下游概念",
            "3.5.3 RxJava 创建型操作符",
            "3.5.4 RxJava变换型操作符",
            "3.5.5 RxJava过滤型操作符",
            "3.5.6 RxJava条件型操作符",
            "3.5.7 RxJava合并型操作符",
            "3.5.8 RxJava异常处理操作符",
            "3.5.9 RxJava线程切换实战",
            "3.5.10 RxJava背压模式",
            "3.5.11 RxJava之Flowable讲解",
            "3.5.12 RxJava配合Retrofit",
            "3.5.13 RxJava泛型高级进阶"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);
        Frag09Adapter adapter = new Frag09Adapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Frag09Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        method_1();
                        break;
                    case 1:

                        break;
                    case 2:
                        Intent intent = new Intent(RxActivity.this, MainActivity2.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(RxActivity.this, MainActivity3.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(RxActivity.this, MainActivity4.class);
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(RxActivity.this, MainActivity5.class);
                        startActivity(intent);
                        break;

                    case 6:
                        intent = new Intent(RxActivity.this, MainActivity6.class);
                        startActivity(intent);
                        break;

                    case 7:
                        intent = new Intent(RxActivity.this, MainActivity7.class);
                        startActivity(intent);
                        break;

                    case 8:
                        intent = new Intent(RxActivity.this, MainActivity8.class);
                        startActivity(intent);
                        break;

                    case 9:
                        intent = new Intent(RxActivity.this, MainActivity9.class);
                        startActivity(intent);
                        break;

                    case 10:
                        intent = new Intent(RxActivity.this, MainActivity10.class);
                        startActivity(intent);
                        break;

                    case 11:
                        intent = new Intent(RxActivity.this, MainActivity11.class);
                        startActivity(intent);
                        break;

                    case 12:
                        intent = new Intent(RxActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;

                    case 13:

                        break;

                }
            }
        });
    }

    private void method_1() {
        // 上游
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                // 上游发射的
                e.onNext("A"); // 使用者自己发射
            }
        })

                // 订阅
                .subscribe(
                        // 下游
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "下游接收 onNext: " + s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    /**
     * just 操作符 创建 Observable
     *
     * @param
     */
    public void r02() {
        // 上游
        Observable.just("A", "B")  // 内部会去发射 A B

                // 订阅
                .subscribe(

                        // 下游
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "onNext: " + s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    /**
     * fromArray 操作符 创建 Observable
     *
     * @param
     */
    public void r03() {

        /*String[] strings = {"1", "2", "3"}; // 内部会去发射 1 2 3

        // 上游 被观察者
        Observable.fromArray(strings)

        // 订阅
        .subscribe(

                // 下游
                new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );*/

        String[] strings = {"张三", "李四", "王五"};

        // for
        for (String string : strings) {
            Log.d(TAG, "r03: " + string);
        }

        Log.d(TAG, "r03: ----- ");

        // RxJava
        Observable.fromArray(strings)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }

    /**
     * 为什么只支持Object ？
     * 上游没有发射有值得事件，下游无法确定类型，默认Object，RxJava泛型 泛型默认类型==Object
     * <p>
     * 做一个耗时操作，不需要任何数据来刷新UI， empty的使用场景之一
     *
     * @param
     */
    public void r04() {
        // 上游无法指定 事件类型
        Observable.empty() // 内部一定会只调用 发射 onComplete 完毕事件

                // 订阅
                .subscribe(

                        // 下游 观察者
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object integer) {
                                // 没有事件可以接受
                                Log.d(TAG, "onNext: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "onComplete: ");

                                // 隐藏 加载框...
                            }
                        }

                        /*// 简化版 观察者
                        new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                // 接受不到
                                // 没有事件可以接受
                                Log.d(TAG, "accept: " + o);
                            }
                        }*/

                );
    }


}
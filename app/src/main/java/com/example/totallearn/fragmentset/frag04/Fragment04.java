package com.example.totallearn.fragmentset.frag04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.totallearn.MainActivity;
import com.example.totallearn.R;
import com.example.totallearn.fragmentset.adapter.Frag09Adapter;
import com.example.totallearn.fragmentset.frag04.retrofit.RetrofitLearnActivity;
import com.example.totallearn.frame_set.retrofit_set.RetrofitActivity;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 需要给出相关文章的网址
 */

public class Fragment04 extends Fragment {

    public static final String TAG = Fragment04.class.getSimpleName();
    Unbinder unbinder;

    //线程池  多任务中避免线程重复的创建与销毁
    private ThreadPoolExecutor mthreadPoolExecutor = new ThreadPoolExecutor(
            3,//池中核心线程数量
            5,//池中最大线程数量
            1,//是非核心线程空闲时要等待下一个任务到来的时间
            TimeUnit.SECONDS,//上面时间属性的单位
            new LinkedBlockingDeque<Runnable>(100));//任务队列

    private MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }


    public String[] data = {
            "0.okhttp详解",
            "1.RxJava 实例",
            "2.RxJava 操作符",
            "3.Retrofit 下载测试",
            "4.线程池"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_04, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Frag09Adapter adapter = new Frag09Adapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        unbinder = ButterKnife.bind(this, view);
        adapter.setOnItemClickListener(new Frag09Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        Intent intent = new Intent(mMainActivity, OkhttpActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mMainActivity, RxjavaActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mMainActivity, RetrofitActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(mMainActivity, RetrofitLearnActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        myTheardPool();
                        break;
                }

            }
        });
        return view;
    }


    public void myTheardPool() {
        for (int i = 0; i < 30; i++) {
            final int num = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Log.d(TAG, "run--" + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            mthreadPoolExecutor.execute(runnable);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


}
/**
 * 1.execute一个线程之后，如果线程池中的线程数未达到核心线程数，则会立马启用一个核心线程去执行。
 * 2.execute一个线程之后，如果线程池中的线程数已经达到核心线程数，且workQueue未满，则将新线程放入workQueue中等待执行。
 * 3.execute一个线程之后，如果线程池中的线程数已经达到核心线程数但未超过非核心线程数，且workQueue已满，则开启一个非核心线程来执行任务。
 * 4.execute一个线程之后，如果线程池中的线程数已经超过非核心线程数，则拒绝执行该任务，采取饱和策略，并抛出RejectedExecutionException异常。
 * demo中设置的任务队列长度为100，所以不会开启额外的5-3=2个非核心线程，如果将任务队列设为28，则在打印第28个任务时，队列会满，
 * 此时会开启2个非核心线程来执行剩下的两个任务。
 * 疑问：每个for循环里都有一个sleep（2000），为何会每隔2s打印三个任务？
 * 原因：因为一开始的时候只是声明runnable对象并且重写run()方法，并没有运行，而后execute(runnable) 才会sleep，
 * 又因为一开始创建线程池的时候声明的核心线程数为3，所以会首先开启三个核心线程，然后执行各自的run方法，虽然有先后顺序，但这之间的间隔很短，
 * 所以2s后同时打印3个任务。
 * <p>
 * <p>
 * <p>
 * 1.shutDown()  关闭线程池，不影响已经提交的任务
 * <p>
 * 2.shutDownNow() 关闭线程池，并尝试去终止正在执行的线程
 * <p>
 * 3.allowCoreThreadTimeOut(boolean value) 允许核心线程闲置超时时被回收
 * <p>
 * 4.submit 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值。
 * <p>
 * 5.beforeExecute() - 任务执行前执行的方法
 * <p>
 * 6.afterExecute() -任务执行结束后执行的方法
 * <p>
 * 7.terminated() -线程池关闭后执行的方法
 */
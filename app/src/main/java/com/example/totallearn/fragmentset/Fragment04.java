package com.example.totallearn.fragmentset;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.totallearn.R;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment04 extends Fragment {

    public static final String TAG = Fragment04.class.getSimpleName();
    private Button f4bt1;
    //线程池  多任务中避免线程重复的创建与销毁
    private ThreadPoolExecutor mthreadPoolExecutor = new ThreadPoolExecutor(
            3,//池中核心线程数量
            5,//池中最大线程数量
            1,//是非核心线程空闲时要等待下一个任务到来的时间
            TimeUnit.SECONDS,//上面时间属性的单位
            new LinkedBlockingDeque<Runnable>(100));//任务队列

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_04,container,false);
        f4bt1 = view.findViewById(R.id.f4_bt1);
        f4bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTheardPool();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
    }

    public void myTheardPool(){
        for (int i = 0; i <30 ; i++) {
            final int num = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Log.d(TAG,"run--"+num);
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
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


}
/**
 * 1.execute一个线程之后，如果线程池中的线程数未达到核心线程数，则会立马启用一个核心线程去执行。
 2.execute一个线程之后，如果线程池中的线程数已经达到核心线程数，且workQueue未满，则将新线程放入workQueue中等待执行。
 3.execute一个线程之后，如果线程池中的线程数已经达到核心线程数但未超过非核心线程数，且workQueue已满，则开启一个非核心线程来执行任务。
 4.execute一个线程之后，如果线程池中的线程数已经超过非核心线程数，则拒绝执行该任务，采取饱和策略，并抛出RejectedExecutionException异常。
 demo中设置的任务队列长度为100，所以不会开启额外的5-3=2个非核心线程，如果将任务队列设为28，则在打印第28个任务时，队列会满，此时会开启2个非核心线程来执行剩下的两个任务。
 疑问：每个for循环里都有一个sleep（2000），为何会每隔2s打印三个任务？
 原因：因为一开始的时候只是声明runnable对象并且重写run()方法，并没有运行，而后execute(runnable) 才会sleep，又因为一开始创建线程池的时候声明的核心线程数为3，所以会首先开启三个核心线程，然后执行各自的run方法，虽然有先后顺序，但这之间的间隔很短，所以2s后同时打印3个任务。



 1.shutDown()  关闭线程池，不影响已经提交的任务

 2.shutDownNow() 关闭线程池，并尝试去终止正在执行的线程

 3.allowCoreThreadTimeOut(boolean value) 允许核心线程闲置超时时被回收

 4.submit 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值。

 5.beforeExecute() - 任务执行前执行的方法

 6.afterExecute() -任务执行结束后执行的方法

 7.terminated() -线程池关闭后执行的方法

 */
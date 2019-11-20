package com.example.totallearn.thread_set;

import com.example.totallearn.utils.MyLogUtil;

/**
 * Created by pateo on 19-2-15.
 */

public class ThreadTest {
    public ThreadTest() {

    }

    public void startCustomThread(){
        new CustomThread().start();
    }

    public void startCustomThread2(){
        new Thread(new CustomThread2()).start();
    }

    public void startDemo(){

    }

    //不同方式的使用
    public class CustomThread extends Thread {
        @Override
        public void run() {
            // 处理耗时逻辑
            MyLogUtil.d("CustomThread", "这里执行耗时操作");
        }
    }

    public class CustomThread2 implements Runnable{

        @Override
        public void run() {
            // 处理耗时操作
            MyLogUtil.d("CustomThread2","耗时操作");
        }
    }

    /*public class Demo implements Callable<String> {
        Callable<String> callable = new Demo();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        @Override
        public String call() throws Exception {
            return "demo";
        }
    }*/
}

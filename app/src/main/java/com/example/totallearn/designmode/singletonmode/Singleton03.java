package com.example.totallearn.designmode.singletonmode;

/**
 * Created by pateo on 19-1-9.
 */
//双重检查的折中模式
public class Singleton03 {

    private static volatile Singleton03 instance;

    private Singleton03() {

    }

    private static Singleton03 getInstance() {
        if (instance == null) {
            //同步代码块
            synchronized (Singleton03.class) {
                if (instance == null) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }
}

package com.example.totallearn.designmode.singletonmode;

/**
 * Created by pateo on 19-1-9.
 */

//单例模式 -> 饿汉式  static是先将实例new 出来的
public final class Singleton01 {

    private static Singleton01 instance = new Singleton01();

    private Singleton01(){

    }

    public static Singleton01 getInstance(){
        return new Singleton01();
    }
}

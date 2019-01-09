package com.example.totallearn.designmode.singletonmode;

/**
 * Created by pateo on 19-1-9.
 */
//单例模式 -> 静态内部类 -> 多线程情况下,使用合理一些 -> 推荐
public final class Singleton04 {

    private Singleton04(){

    }

    public static Singleton04 getInstance(){
            return SingletonHolder.singleton04;
    }

    public static class SingletonHolder{
        private static Singleton04 singleton04 = new Singleton04();
    }
}

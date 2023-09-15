package designmode.singletonmode;

/**
 * Created by pateo on 19-1-9.
 */
//单例模式->懒汉式->单线程
public final class Singleton02 {

    private static Singleton02 instance;

    private Singleton02() {

    }

    public static Singleton02 getInstance() {
        if (instance == null) {
            instance = new Singleton02();
        }
        return instance;
    }

}

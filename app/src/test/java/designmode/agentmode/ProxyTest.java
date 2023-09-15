package designmode.agentmode;

/**
 目标对象实现目标接口 , 代理对象持有目标对象的引用 , 代理对象可以创建一个方法来调目标对象中的方法
 */

public class ProxyTest {

    public ProxyTest() {
        Proxy proxy = new Proxy();
        proxy.Request();
    }
}

//抽象主题  目标接口
interface Subject {
    void Request();
}

//真实主题  目标对象
class RealSubject implements Subject {
    public void Request() {
        System.out.println("访问真实主题方法...");
    }
}


//代理对象
class Proxy implements Subject {
    private RealSubject realSubject;

    public void Request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }
}
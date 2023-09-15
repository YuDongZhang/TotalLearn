package designmode.adaptermode;

/**
 首先：目标接口->Target
 其次：被适配者->Adaptee
 最后：适配器->Adapter

 有两个特点（规范）
 特点一：类适配器模式->需要继承被适配者(类)
 Adapter extends Adaptee
 特点二：类适配器模式->需要实现目标接口->Target
 Adapter implements Target
 */



//目标接口
interface Target {
    public void request();
}

//适配者接口
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}

//类适配器类
class ClassAdapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}

//客户端代码
public class ClassAdapterTest {

    public ClassAdapterTest() {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
        //说明经过转化之后 , 还是目标接口调目标接口的方法 不过已经好很多了, 调到适配者接口的方法
    }

}
package designmode.flyweightmode.example1;

import java.util.HashMap;

/**
 * Created by pateo on 19-1-22.
 */

public class FlyweightPattern {
    public FlyweightPattern() {
        FlyweightFactory factory=new FlyweightFactory();
        Flyweight f01=factory.getFlyweight("a");
        Flyweight f02=factory.getFlyweight("a");
        Flyweight f03=factory.getFlyweight("a");
        Flyweight f11=factory.getFlyweight("b");
        Flyweight f12=factory.getFlyweight("b");
        f01.operation(new UnsharedConcreteFlyWeight("第1次调用a。"));
        f02.operation(new UnsharedConcreteFlyWeight("第2次调用a。"));
        f03.operation(new UnsharedConcreteFlyWeight("第3次调用a。"));
        f11.operation(new UnsharedConcreteFlyWeight("第1次调用b。"));
        f12.operation(new UnsharedConcreteFlyWeight("第2次调用b。"));
    }
}

//非享元角色
class UnsharedConcreteFlyWeight{
    private String info;

    public UnsharedConcreteFlyWeight(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

//抽象享元角色
interface Flyweight{
    public void operation(UnsharedConcreteFlyWeight state);
}

//具体的享元角色
class ConcreteFlyweight implements Flyweight{

    private String key;

    public ConcreteFlyweight(String key) {
        this.key = key;
        System.out.println("具体享元"+key+"被创建！");
    }

    @Override
    public void operation(UnsharedConcreteFlyWeight outState) {
        System.out.print("具体享元"+key+"被调用，");
        System.out.println("非享元信息是:"+outState.getInfo());
    }
}

//享元工厂角色
class  FlyweightFactory{
    private HashMap<String,Flyweight> flyweights = new HashMap<>();
    public Flyweight getFlyweight(String Key){
        Flyweight flyweight = flyweights.get(Key);

        if (flyweight!=null){
            System.out.println("具体享元"+Key+"已经存在，被成功获取！");
        }else {
            flyweight = new ConcreteFlyweight(Key);
            flyweights.put(Key,flyweight);
        }
        return flyweight;
    }
}


/*
具体享元a被创建！
具体享元a已经存在，被成功获取！
具体享元a已经存在，被成功获取！
具体享元b被创建！
具体享元b已经存在，被成功获取！
具体享元a被调用，非享元信息是:第1次调用a。
具体享元a被调用，非享元信息是:第2次调用a。
具体享元a被调用，非享元信息是:第3次调用a。
具体享元b被调用，非享元信息是:第1次调用b。
具体享元b被调用，非享元信息是:第2次调用b。
 */
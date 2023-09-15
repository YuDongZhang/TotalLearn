package designmode.behaviortype.strategymode.example1;

/**
 *
 抽象策略（Strategy）类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接口调用不同的算法，
 一般使用接口或抽象类实现。
 具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现。
 环境（Context）类：持有一个策略类的引用，最终给客户端调用。


 */

public class StrategyPattern {
    public StrategyPattern() {
        ContextStrategy c=new ContextStrategy();
        Strategy s=new ConcreteStrategyA();  //看你new 的是谁的子类 , 传入调自己的方法 , 方法中有父类调方法 其实调的是子类的方法
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-----------------");
        s=new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }
}

//抽象策略类
interface Strategy{
    public void strategyMethod();//策略方法  也可以说是一个算法
}

//具体的策略类A
class ConcreteStrategyA implements Strategy{

    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}

//具体策略类B
class ConcreteStrategyB implements Strategy{

    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}

//环境类
class ContextStrategy{
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }
}




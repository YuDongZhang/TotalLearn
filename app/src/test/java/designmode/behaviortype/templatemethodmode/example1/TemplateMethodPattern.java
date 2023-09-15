package designmode.behaviortype.templatemethodmode.example1;

/**
 * Created by pateo on 19-1-23.
 */

public class TemplateMethodPattern {
    public TemplateMethodPattern() {
        AbstarctClass tm=new ConcreteClass();
        tm.TemplateMethod();
    }
}

//抽象类
abstract class AbstarctClass{
    public void TemplateMethod(){//模板方法 定义了算法的骨架，按某种顺序调用其包含的基本方法。
        specificMethod();
        abstarctMethod1();
        abstarctMethod2();
    }

    protected abstract void abstarctMethod1();//抽象方法1

    protected abstract void abstarctMethod2();//抽象方法2

    public void specificMethod(){ //具体方法  在抽象类中已经实现，在具体子类中可以继承或重写它。
        System.out.println("抽象类中的具体方法被调用...");
    }
}


//具体子类
class ConcreteClass extends AbstarctClass{

    @Override
    protected void abstarctMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    protected void abstarctMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}

/*
抽象类中的具体方法被调用...
抽象方法1的实现被调用...
抽象方法2的实现被调用...
 */
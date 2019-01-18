package com.example.totallearn.designmode.bridgemode;

/**
 * Created by pateo on 19-1-18.
 * 看下面的代码你就可以看到 抽象的  和实现的部分是分开的 , 本来是一个简单的继承就可以搞定的
 *
 *
 *
 *套路:
 * 实现化角色 = new 具体实现化角色 , (它的子类)
 * 抽象化角色 = new 扩展抽象化角色(实现化角色)
 */

public class BridgeTest {

    public BridgeTest() {
        Implementor imple = new ConcreteImplementorA(); //这个是实现者
        Abstraction abs = new RefinedAbstract(imple);//这是抽象者
        abs.Opration();
    }
}

//实现化角色
interface Implementor{
    public void OprationImpl();
}

//具体实现化角色
class ConcreteImplementorA implements Implementor{

    @Override
    public void OprationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问" );
    }
}

//抽象化角色
abstract class Abstraction{
    protected Implementor imple;

    public Abstraction(Implementor imple) {
        this.imple = imple;
    }

    public abstract void Opration();
}


//扩展的抽象化角色
class RefinedAbstract extends Abstraction{

    public RefinedAbstract(Implementor imple) {
            super(imple);
    }

    @Override
    public void Opration() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问" );
        imple.OprationImpl();
    }
}
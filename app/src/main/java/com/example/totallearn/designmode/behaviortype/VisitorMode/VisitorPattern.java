package com.example.totallearn.designmode.behaviortype.VisitorMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pateo on 19-1-30.
 * 确实看出来是多对多的
 */

public class VisitorPattern {
    public VisitorPattern() {
        ObjectStructure os=new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor=new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor=new ConcreteVisitorB();
        os.accept(visitor);
    }
}

//抽象的访问者
interface Visitor {
    void visit(ConcreteElementA concreteElementA);//抽象的访问者中传入参数 为具体的元素

    void visit(ConcreteElementB concreteElementB);

}

//具体的访问者 A 类
class ConcreteVisitorA implements Visitor {

    public void visit(ConcreteElementA element)
    {
        System.out.println("具体访问者A访问-->"+element.operationA());
    }
    public void visit(ConcreteElementB element)
    {
        System.out.println("具体访问者A访问-->"+element.operationB());
    }
}

//具体的访问者 B 类
class ConcreteVisitorB implements Visitor {

    public void visit(ConcreteElementA element)
    {
        System.out.println("具体访问者B访问-->"+element.operationA());
    }
    public void visit(ConcreteElementB element)
    {
        System.out.println("具体访问者B访问-->"+element.operationB());
    }
}

//抽象元素
interface Element {
    void accept(Visitor visitor);
}

//具体元素 A 类
class ConcreteElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    public String operationA() {
        return "具体元素A的操作。";
    }

}

//具体元素 B 类
class ConcreteElementB implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "具体元素B的操作。";
    }
}

//对象结构角色
class ObjectStructure {
    private List<Element> list=new ArrayList<Element>();
    public void accept(Visitor visitor)
    {
        Iterator<Element> i=list.iterator();
        while(i.hasNext())
        {
            ((Element) i.next()).accept(visitor);//对于元素的循环 传入访问者
        }
    }
    public void add(Element element)
    {
        list.add(element);
    }
    public void remove(Element element)
    {
        list.remove(element);
    }
}

/*
具体访问者A访问-->具体元素A的操作。
具体访问者A访问-->具体元素B的操作。
------------------------
具体访问者B访问-->具体元素A的操作。
具体访问者B访问-->具体元素B的操作。
 */
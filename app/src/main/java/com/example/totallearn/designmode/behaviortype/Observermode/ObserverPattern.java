package com.example.totallearn.designmode.behaviortype.Observermode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pateo on 19-1-28.
 */

public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        subject.add(observer1);
        subject.add(observer2);
        subject.notifyObserver();//在这个参数中可以传个 boolean 值   通知的时候做了个循环来进行分个通知

    }

}

//抽象目标 被观察者
abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    //增加观察者方法
    public void add(Observer observer) {
        observers.add(observer);
    }

    //删除观察者方法
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public abstract void notifyObserver();//通知观察者的方法

}

//具体目标  具体被观察者
class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");

        for (Object obs : observers) {
            ((Observer) obs).response();
        }
    }


}

//抽象观察者
interface Observer {
    void response();//反应
}

//具体的观察者1
class ConcreteObserver1 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者1--1作出反应！");
    }
}

//具体的观察者2
class ConcreteObserver2 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者2--2作出反应！");
    }
}
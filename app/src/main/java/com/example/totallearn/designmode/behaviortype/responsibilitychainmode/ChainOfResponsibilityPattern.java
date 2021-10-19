package com.example.totallearn.designmode.behaviortype.responsibilitychainmode;

/**
 * Created by pateo on 19-1-28.
 */

public class ChainOfResponsibilityPattern {

    public ChainOfResponsibilityPattern() {
        //组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handlerRequest("two");
    }
}

//抽象的处理者角色
abstract class Handler {
    private Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    //处理请求的方法
    public abstract void handlerRequest(String request);
}

//具体的处理者 角色1
class ConcreteHandler1 extends Handler {

    @Override
    public void handlerRequest(String request) {
        if (request.equals("one")) {
            System.out.println("具体处理者1负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handlerRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }

    }
}

class ConcreteHandler2 extends Handler {

    @Override
    public void handlerRequest(String request) {
        if (request.equals("two")) {
            System.out.println("具体处理者2负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handlerRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}
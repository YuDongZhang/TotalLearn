package com.example.totallearn.designmode.behaviortype.commandmode.example1;

/**
 * 抽象命令 = new 命令子类
 * 调用者 = new 调用者(抽象命令)
 * 调用者.执行方法()
 */

public class CommandPattern {
    public CommandPattern() {
        Command cmd=new ConcreteCommand();
        Invoker ir=new Invoker(cmd);
        System.out.println("客户访问调用者的call()方法...");
        ir.call();
    }
}

//调用者
class Invoker{

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call(){
        System.out.println("调用者执行命令command...");
        command.execute();
    }
}

//抽象命令
interface Command{
    public abstract void execute();
}

//具体命令
class ConcreteCommand implements Command{

    private Receiver receiver;

    ConcreteCommand() {
        receiver = new Receiver();
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

//接收者
class Receiver{
    public void action(){
        System.out.println("接收者的action()方法被调用...");
    }
}
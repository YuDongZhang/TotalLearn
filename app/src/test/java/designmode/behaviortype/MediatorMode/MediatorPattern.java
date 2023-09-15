package designmode.behaviortype.MediatorMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pateo on 19-1-29.
 *
 *
 *
 *
 *
 */

public class MediatorPattern {
    public MediatorPattern() {
        Mediator md = new ConcreteMediator();
        Colleague c1 , c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-----------------");
        c2.send();

    }
}

//抽象中介者
abstract class Mediator{
    public abstract void register(Colleague colleague);
    public abstract void relay(Colleague c);//转发

}

//具体中介者
class ConcreteMediator extends Mediator{
    private List<Colleague> colleagues = new ArrayList<>();


    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)){
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague c) {
        for (Colleague ob : colleagues){
            if (!ob.equals(c)){
                ((Colleague)ob).receive();
            }
        }
    }
}

//抽象同事类
abstract class Colleague{
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();
    public abstract void send();

}

//具体同事类
class ConcreteColleague1 extends Colleague {

    @Override
    public void receive() {
        System.out.println("具体同事类1 收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类1 发出请求。");
        mediator.relay(this);//请中介者转发
    }
}

//具体同事类
class ConcreteColleague2 extends Colleague{

    @Override
    public void receive() {
        System.out.println("具体同事类2 收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2 发出请求。");
        mediator.relay(this);//请中介者转发
    }
}
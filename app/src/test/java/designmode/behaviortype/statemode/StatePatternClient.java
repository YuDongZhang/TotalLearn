package designmode.behaviortype.statemode;

/**
 * Created by pateo on 19-1-28.
 */

public class StatePatternClient {

    public StatePatternClient() {

        Context context=new Context();    //创建环境
        context.Handle();    //处理请求
        context.Handle();
        context.Handle();
        context.Handle();
    }
}


//环境类
class Context{
    private State state;

    public Context() {
       this.state = new ConcreteStateA();
    }

    //读取状态
    public State getState() {
        return state;
    }

    //设置新状态
    public void setState(State state) {
        this.state = state;
    }

    //对请求做处理
    public void Handle(){
        state.Handler(this);
    }
}

//抽象状态类
abstract class State{
    public abstract void Handler(Context context);
}

//具体状态A类
class ConcreteStateA extends State{

    @Override
    public void Handler(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new ConcreteStateB());
    }
}

//具体状态B类
class ConcreteStateB extends State{

    @Override
    public void Handler(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}
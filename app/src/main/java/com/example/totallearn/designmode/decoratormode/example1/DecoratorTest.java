package com.example.totallearn.designmode.decoratormode.example1;

import com.example.totallearn.utils.Log;

/**
 * Created by pateo on 19-1-21.
 */

//装饰者  测试
public class DecoratorTest {

    public DecoratorTest() {
        Component p = new ConcreteComponent();
        p.operation();
        Log.d("-------","----------");
        Component d = new ConcreteDecorator(p);
        d.operation();
    }
}

//抽象构件角色
interface Component{
    public void operation();
}

//具体构件角色
class ConcreteComponent implements Component{

    public ConcreteComponent() {
        Log.d("ConcreteComponent","创建具体构件角色");
    }

    @Override
    public void operation() {
        Log.d("ConcreteComponent","调用具体构件角色的方法operation()");
    }
}

//抽象装饰角色
class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

//具体装饰角色
class ConcreteDecorator extends Decorator{

    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation(){
        super.operation();
        addedFunction();
    }

    public void addedFunction(){
        Log.d("ConcreteDecorator","为具体构件角色增加额外的功能addedFunction()");
    }
}

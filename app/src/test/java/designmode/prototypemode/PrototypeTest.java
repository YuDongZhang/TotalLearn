package designmode.prototypemode;

//具体原型类
class Realizetype implements Cloneable {
    Realizetype() {
        System.out.println("具体原型创建成功！---创建");
    }

    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！---复制");
        return (Realizetype) super.clone();
    }
}

//原型模式的测试类
public class PrototypeTest {

    public PrototypeTest() throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println("obj1==obj2?" + (obj1 == obj2));
    }
}
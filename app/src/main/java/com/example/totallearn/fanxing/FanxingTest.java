package com.example.totallearn.fanxing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by pateo on 19-2-18.
 *
 * https://www.jianshu.com/p/4caf2567f91d
 *
 */

//这个例子表明编译过程中并没有根据参数生成新的类型
public class FanxingTest {
    public FanxingTest() {
        //分个的去测试 test_1()的方法
        test_1();
    }

    private void test_1() {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.print(c1 == c2);
    }
    /* output
    true

    在 List<String> 中添加 Integer 将不会通过编译，但是List<Sring>与List<Integer>在运行时的确是同一种类型。
    */


    //例子, 这个例子表明类的参数类型跟传进去的类型没有关系，泛型参数只是`占位符`
    public class Table {
    }

    public class Room {
    }

    public class House<Q> {
    }

    public class Particle<POSITION, MOMENTUM> {
    }

    private void test_2() {
        List<Table> tableList = new ArrayList<Table>();
        Map<Room, Table> maps = new HashMap<Room, Table>();
        House<Room> house = new House<Room>();
        Particle<Long, Double> particle = new Particle<Long, Double>();
        System.out.println(Arrays.toString(tableList.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(maps.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(house.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(particle.getClass().getTypeParameters()));
    }
    /*
    我们在运行期试图获取一个已经声明的类的类型参数，发现这些参数依旧是‘形参’，并没有随声明改变。也就是说在运行期，我们
    是拿不到已经声明的类型的任何信息。

    编译器会虽然在编译过程中移除参数的类型信息，但是会保证类或方法内部参数类型的一致性。
     */


    private void test_3() {
        List<String> stringList = new ArrayList<String>();
        //可以通过编译
        stringList.add("wakaka");
        //编译不通过
        //stringList.add(new Integer(0));

        //List.java
        //public interface List<E> extends Collection<E> {
            //...
           // boolean add(E e);
            //...
       // }

    }

    /*
    List的参数类型是E，add方法的参数类型也是E，他们在类的内部是一致的，所以添加Integer类型的对象到stringList违反了
    内部类型一致，不能通过编译。
    重用 extends 关键字。通过它能给与参数类型添加一个边界。
    泛型参数将会被擦除到它的第一个边界（边界可以有多个）。编译器事实上会把类型参数替换为它的第一个边界的类型。如果没有指明边界，
    那么类型参数将被擦除到Object。下面的例子中，可以把泛型参数T当作HasF类型来使用。
     */


    // HasF.java
    public interface HasF {
        void f();
    }

    //Manipulator.java
    public class Manipulator<T extends HasF> {
        T obj;
        public T getObj() {
            return obj;
        }
        public void setObj(T obj) {
            this.obj = obj;
        }
    }

    //extend关键字后后面的类型信息决定了泛型参数能保留的信息。



    //Java中擦除的基本原理
    /*
    刚看到这里可能有些困惑，一个泛型类型没有保留具体声明的类型的信息，那它是怎么工作的呢？在把《Java编程思想》书中这里
    的边界与上文的边界区分开来之后，终于想通了。Java的泛型类的确只有一份字节码，但是在使用泛型类的时候编译器做了特殊的处理。
    这里根据作者的思路，自己动手写了两个类SimpleHolder和GenericHolder，然后编译拿到两个类的字节码，直接贴在这里：
     */
    public class SimpleHodler{
        private Object obj;
        public Object getObj(){
            return obj;
        }
    }






    //类型判断问题
    class Building {}
    class House2 extends Building {}
    public class ClassTypeCapture<T> {
        Class<T> kind;
        public ClassTypeCapture(Class<T> kind) {
            this.kind = kind;
        }
        public boolean f(Object arg) {
            return kind.isInstance(arg);
        }

    }

    private void test_5(){
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));
        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<House>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.print(ctt2.f(new House()));
    }
    //output
    //true
    //true
    //false
    //true



    /*
    泛型参数的类型无法用instanceof关键字来做判断。所以我们使用类类型来构造一个类型判断器，判断一个实例是否为特定的类型。
     */
    public class Erased<T>{
        private final int SIZE = 100;
        public  void f(Object arg){
            /*
            //编译不通过
            if (arg instanceof T) {
            }
            //编译不通过
            T var = new T();
            //编译不通过
            T[] array = new T[SIZE];
            //编译不通过
            T[] array = (T) new Object[SIZE];
            */
        }
    }





    //擦除的补偿
    //1.类型判断的问题
    class Building_3 {}
    class House_3 extends Building_3 {}
    public class ClassTypeCapture_3<T> {
        Class<T> kind;
        public ClassTypeCapture_3(Class<T> kind) {
            this.kind = kind;
        }
        public boolean f(Object arg) {
            return kind.isInstance(arg);
        }
    }

    private void test_6(){
        ClassTypeCapture_3<Building_3> ctt1 = new ClassTypeCapture_3<>(Building_3.class);
        System.out.println(ctt1.f(new Building_3()));
        System.out.println(ctt1.f(new House_3()));
        ClassTypeCapture_3<House_3> ctt2 = new ClassTypeCapture_3<House_3>(House_3.class);
        System.out.println(ctt2.f(new Building_3()));
        System.out.print(ctt2.f(new House_3()));
    }

    //output
    //true
    //true
    //false
    //true

    /*
    泛型参数的类型无法用instanceof关键字来做判断。所以我们使用类类型来构造一个类型判断器，判断一个实例是否为特定的类型。
     */


    //2.创建类型实例
    /*
    Erased.java中不能new T()的原因有两个，一是因为擦除，不能确定类型；而是无法确定T是否包含无参构造函数。
    为了避免这两个问题，我们使用显式的工厂模式：

    例子2
     */

    interface IFactory<T>{
        T create();
    }

    class Foo2<T>{
        private T x;

        public <F extends IFactory<T>> Foo2(F factory){
            x = factory.create();
        }
    }

    class IntergerFactory implements IFactory<Integer>{

        @Override
        public Integer create() {
            return new Integer(0);
        }
    }

    static class Widget{
        public static class Factory implements IFactory<Widget>{

            @Override
            public Widget create() {
                return new Widget();
            }
        }
    }

    public void test_7(){
        new Foo2<Integer>(new IntergerFactory());
        new Foo2<Widget>(new Widget.Factory());
    }
    //通过特定的工厂类实现特定的类型能够解决实例化类型参数的需求。




    //3. 创建泛型数组
    /*
    一般不建议创建泛型数组。尽量使用ArrayList来代替泛型数组。但是在这里还是给出一种创建泛型数组的方法。
     */
    public class GenericArrayWithTypeToken<T> {
        private T[] array;

        @SuppressWarnings("unchecked")
        public GenericArrayWithTypeToken(Class<T> type, int sz) {
            array = (T[]) Array.newInstance(type, sz);
        }

        public void put(int index, T item) {
            array[index] = item;
        }

        public T[] rep() {
            return array;
        }

        public void test_8() {
            GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<Integer>(Integer.class, 10);
            Integer[] ia = gai.rep();
        }
    }
    //这里我们使用的还是传参数类型，利用类型的newInstance方法创建实例的方式。




    //边界
    //这里Java重用了 extend关键字。边界可以将类型参数的范围限制到一个子集当中。
    /*
    interface HasColor {
    Color getColor();
}

class Colored<T extends HasColor> {
    T item;

    public Colored(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public Color color() {
        return item.getColor();
    }
}

class Dimension {
    public int x, y, z;
}

class ColoredDemension<T extends HasColor & Dimension> {
    T item;

    public ColoredDemension(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }

}

interface Weight {
    int weight();
}

class Solid<T extends Dimension & HasColor & Weight> {
    T item;

    public Solid(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    Color color() {
        return item.getColor();
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }

    int weight() {
        return item.weight();
    }
}

class Bounded extends Dimension implements HasColor, Weight {
    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}

public class BasicBound {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<Bounded>(new Bounded());
        solid.color();
        solid.weight();
        solid.getZ();
    }
}


extends关键字声明中，有两个要注意的地方：

类必须要写在接口之前；
只能设置一个类做边界，其它均为接口。

     */


    /**
     * 通配符
     * 协变
     */
    /*

    public class Holder<T> {
        private T value;

        public Holder(T apple) {
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            return value != null && value.equals(o);
        }

        public static void main(String[] args) {
            Holder<Apple> appleHolder = new Holder<Apple>(new Apple());
            Apple d = new Apple();
            appleHolder.setValue(d);

            // 不能自动协变
            // Holder<Fruit> fruitHolder=appleHolder;

            // 借助 ？ 通配符和 extends 关键字可以实现协变
            Holder<? extends Fruit> fruitHolder = appleHolder;

            // 返回一个Fruit，因为添加边界之后返回的对象是 ? extends Fruit,
            // 可以把它转型为Apple，但是在不知道具体类型的时候存在风险
            d = (Apple) fruitHolder.getValue();

            //Fruit以及Fruit的父类，就不需要转型
            Fruit fruit = fruitHolder.getValue();
            Object obj = fruitHolder.getValue();

            try {
                Orange c = (Orange) fruitHolder.getValue();
            } catch (Exception e) {
                System.out.print(e);
            }

            // 编译不通过，因为编译阶段根本不知道子类型到底是什么类型
            //        fruitHolder.setValue(new Apple());
            //        fruitHolder.setValue(new Orange());

            //这里是可以的因为equals方法接受的是Object作为参数，并不是 ? extends Fruit
            System.out.print(fruitHolder.equals(d));
        }
    }


    在Java中父类型可以持有子类型。如果一个父类的容器可以持有子类的容器，那么我们就可以称为发生了协变。在java中，数组是
    自带协变的，但是泛型的容器没有自带协变。我们可以根据利用边界和通配符?来实现近似的协变。

    Holder<? extends Fruit>就是一种协变的写法。它表示一个列表，列表持有的类型是Fruit或其子类。

    这个Holder<? extends Fruit>运行时持有的类型是未知的，我们只知道它一定是Fruit的子类。正因为如此，所以我们无法向
    这个holder中放入任何类型的对象，Object类型的对象也不可以。但是，调用它的返回方法却是可以的。因为边界明确定义了它
    是Fruit类型的子类。

    */

    /**
     * 逆变
     */

    /*
        package wildcard;

    import java.util.ArrayList;
    import java.util.List;

    public class GenericWriting {
        static <T> void writeExact(List<T> list, T item) {
            list.add(item);
        }

        static List<Apple> apples = new ArrayList<Apple>();
        static List<Fruit> fruits = new ArrayList<Fruit>();

        static void f1() {
            writeExact(apples, new Apple());
            //this cannot be compile,said in Thinking in Java
            writeExact(fruits, new Apple());
        }

        static <T> void writeWithWildcard(List<? super T> list, T item) {
            list.add(item);
        }

        static void f2() {
            writeWithWildcard(apples, new Apple());
            writeWithWildcard(fruits, new Apple());
        }

        static <T> readWithWildcard(List<? super T> list, int index) {
            //Compile Error, required T but found Object
            return list.get(index);
        }
        public static void main(String[] args) {
            f1();
            f2();
        }
    }

     */

    /*
    如果一个类的父类型容器可以持有该类的子类型的容器，我们称这种关系为逆变。声明方式List<? super Integer>, List<? super T> list。

    不能给泛型参数给出一个超类型边界；即不能声明List<T super MyClass>。

    上面的例子中，writeExact(fruits,new Apple());在《Java编程思想》中说是不能通过编译的，但我试了一下，在Java1.6，
    Java1.7中是可以编译的。不知道是不是编译器比1.5版本升级了。

    由于给出了参数类型的‘下界’，所以我们可以在列表中添加数据而不会出现类型错误。但是使用get方法获取返回类型的时候要注意，
    由于声明的类型区间是Object到T具有继承关系的类。所以返回的类型为了确保没有问题，都是以Object类型返回回来的。比如过例子中
    list.get(index)的返回类型就是Object。

     */

    /*
    一些需要注意的问题

        1. 任何基本类型都不能作为类型参数
        2. 实现参数化接口
        例子：
        interface Payable<T>{}
        class Employee implements Payable<Employee> {}
        //Compile Error
        class Hourly extends Employee implements Payable<Hourly> {}

        因为擦除的原因，Payable<Employee> 与 Payable<Hourly>简化为相同的Payable<Object>，例子中的代码意味着重
        复两次实现相同的接口。但他们的参数类型却是不相同的。
        3. 转型和警告
        使用带有泛型类型参数的转型或者instanceof不会有任何效果。因为他们在运行时都会被擦除到上边界上。所以转型的时候
        用的类型实际上是上边解对应的类型。
        4. 重载
        //Compile Error. 编译不能通过
        public class UseList<W,T>{
            void f(List<T> v){}
            void f(List<W> v){}
        }

        由于擦除的原因，重载方法将产生相同的类型签名。避免这种问题的方法就是换个方法名。
        5. 基类劫持接口
        例子：
        public class ComparablePet implements Comparable<ComparablePet>{
            public int compareTo(ComparablePet arg) {return 0;}
        }
        class Cat extends ComparablePet implements Comparable<Cat>{
            // Error: Comparable connot be inherited with
            // different arguments: <Cat> and <ComparablePet>
            public int compareTo(Cat arg);
        }

        父类中我们为Comparable确定了ComparablePet参数，那么其它任何类型都不能再与ComparablePet之外的对象再比较。子类中不能对同一个接口用不同的参数实现两次。这有点类似于第四点中的重载。
        但是我们可以在子类中覆写父类中的方法。
        关于泛型问题就先了解这么多，有什么不对的地方还请大家指正。也欢迎小伙伴们一起交流。

     */

}


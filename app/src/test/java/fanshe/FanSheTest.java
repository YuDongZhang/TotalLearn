package fanshe;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by pateo on 19-2-22.
 * https://www.cnblogs.com/luoxn28/p/5686794.html
 * <p>
 * 每个类都会产生一个对应的Class对象，也就是保存在.class文件。所有类都是在对其第一次使用时，动态加载到JVM的，
 * 当程序创建一个对类的静态成员的引用时，就会加载这个类。Class对象仅在需要的时候才会加载，static初始化是在类加载时进行的。
 * 小结:class生成 .class 文件 , 在第一次使用的时候加载到 jvm 虚拟机中
 * <p>
 * <p>
 * 在f3中有使用
 */

public class FanSheTest {
    public FanSheTest() {
        //测试一
        System.out.println(XYZ.name);

        //测试2
        //不会初始化静态块
        Class clazz1 = Base.class;
        System.out.println("---------");
        //会初始化
        try {
            Class clazz2 = Class.forName("zzz.Base");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //测试3
        Base base = new Derived();
        if (base instanceof Derived) {
            // 这里可以向下转换了
            System.out.println("ok");
        } else {
            System.out.println("not ok");
        }

        //测试4
        Person person = new Person("张雨东", 29);
        Class clazz = person.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            /*
             PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
             Method method = descriptor.getReadMethod();
             Object value = method.invoke(person);

             System.out.println(key + ":" + value);
             */
        }

        //测试5
        Test5 test5 = new Test5();


    }
}

class XYZ {
    public static String name = "类xyz";

    static {
        System.out.println("xyz静态块");
    }

    public XYZ() {
        System.out.println("xyz构造了");
    }
    //打印结果
    //xyz静态块
    //类xyz
}


/**
 * 类加载器 首先会检查类的class对象是否已被加载过 , 如未加载 , 默认类加载器会根据类名查找 .class 文件
 * <p>
 * 想在运行时使用类型信息,必须获取对象(如类 Base对象)的class对象的引用 , 使用功能 Class.forName("Base")可以实现该目的
 * 注意，有一点很有趣，使用功能”.class”来创建Class对象的引用时，不会自动初始化该Class对象，使用forName()会自动初始化该Class对象。
 * 为了使用类而做的准备工作一般有以下3个步骤：
 * <p>
 * 加载：由类加载器完成，找到对应的字节码，创建一个Class对象
 * 链接：验证类中的字节码，为静态域分配空间
 * 初始化：如果该类有超类，则对其初始化，执行静态初始化器和静态初始化块
 * 看下面实例:
 */

class Base {
    static int num = 1;

    static {
        System.out.println("Base" + num);
    }
}

/* 查看构造方法中的测试
    public class Main {
        public static void main(String[] args) {
            // 不会初始化静态块
            Class clazz1 = Base.class;
            System.out.println("------");
            // 会初始化
            Class clazz2 = Class.forName("zzz.Base");
        }
    }
 */


/**
 * 2.类型转换前先做检查
 * 　编译器将检查类型向下转型是否合法，如果不合法将抛出异常。向下转换类型前，可以使用instanceof判断。
 */

class Derived extends Base {
};

/*  可以在上面测试 三
    public static void main(String[] args) {
        Base base = new Derived();
        if (base instanceof Derived) {
            // 这里可以向下转换了
            System.out.println("ok");
        }
        else {
            System.out.println("not ok");
        }
    }
 */


/**
 * 3、反射：运行时类信息
 * Class类与java.lang.reflect类库一起对反射进行了支持，该类库包含Field、Method和Constructor类，
 * 这些类的对象由JVM在启动时创建，用以表示未知类里对应的成员 . 这样的话就可以使用Contructor创建新的对象，
 * 用get()和set()方法获取和修改类中与Field对象关联的字段，用invoke()方法调用与Method对象关联的方法。另外，
 * 还可以调用getFields()、getMethods()和getConstructors()等许多便利的方法，以返回表示字段、方法、
 * 以及构造器对象的数组，这样，对象信息可以在运行时被完全确定下来，而在编译时不需要知道关于类的任何事情。
 * <p>
 * 反射机制并没有什么神奇之处，当通过反射与一个未知类型的对象打交道时，JVM只是简单地检查这个对象，看它属于哪个特定的类。
 * 因此，那个类的.class对于JVM来说必须是可获取的，要么在本地机器上，要么从网络获取。所以对于RTTI和反射之间的真正区别只在于：
 * <p>
 * RTTI，编译器在编译时打开和检查.class文件
 * 反射，运行时打开和检查.class文件
 */

class Person implements Serializable {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //查看上面测试4
    // get/set方法

    /*
            public static void main(String[] args) {
            Person person = new Person("luoxn28", 23);
            Class clazz = person.getClass();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
                Method method = descriptor.getReadMethod();
                Object value = method.invoke(person);

                System.out.println(key + ":" + value);

            }
        }

    以上通过getReadMethod()方法调用类的get函数，可以通过getWriteMethod()方法来调用类的set方法。通常来说，我们不需要使
    用反射工具，但是它们在创建动态代码会更有用，反射在Java中用来支持其他特性的，例如对象的序列化和JavaBean等。
     */
}


/**
 * 4、动态代理
 * 代理模式是为了提供额外或不同的操作，而插入的用来替代”实际”对象的对象，这些操作涉及到与”实际”对象的通信，因此代理通常充当中间人角色。
 * Java的动态代理比代理的思想更前进了一步，它可以动态地创建并代理并动态地处理对所代理方法的调用。在动态代理上所做的所有调用都会被重定向到
 * 单一的调用处理器上，它的工作是揭示调用的类型并确定相应的策略。以下是一个动态代理示例：
 * <p>
 * 总结:获得父接口 , 来进行代理 , 代理就是代其处理
 */

//接口和实现类
interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {
    public void doSomething() {
        System.out.println("doSomething.");
    }

    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}


//动态代理对象处理器：
class DynamicProxyHandler implements InvocationHandler {
    private Object proxyed;

    public DynamicProxyHandler(Object proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("代理工作了.");
        return method.invoke(proxyed, args);
    }
}

//测试类：这个需要在上面来进行实现

class Test5 {

    public Test5() {
        RealObject real = new RealObject();
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(), new Class[]{Interface.class},
                new DynamicProxyHandler(real));

        proxy.doSomething();
        proxy.somethingElse("luoxn28");  //代替了那个调用了她的方法
    }

}

/*
通过调用Proxy静态方法Proxy.newProxyInstance()可以创建动态代理，这个方法需要得到一个类加载器，一个你希望该代理实现的
接口列表(不是类或抽象类)，以及InvocationHandler的一个实现类。动态代理可以将所有调用重定向到调用处理器，因此通常会调用
处理器的构造器传递一个”实际”对象的引用，从而将调用处理器在执行中介任务时，将请求转发。

   从打印可以看处理是多次执行了方法
 */
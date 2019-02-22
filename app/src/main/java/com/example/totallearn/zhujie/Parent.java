package com.example.totallearn.zhujie;

/**
 * Created by pateo on 19-2-20.
 * https://www.jianshu.com/p/9ca78aa4ab4d
 * 注解:
 *
 降低项目的耦合度。
 自动完成一些规律性的代码。
 自动生成java代码，减轻开发者的工作量。

 这样才会作为框架的使用
 */
@AInherited("Inherited")
@BNotInherited("没Inherited")
public class Parent {

    @AInherited("Inherited")
    @BNotInherited("没Inherited")
    public void testOverride(){

    }

    @AInherited("Inherited")
    @BNotInherited("没Inherited")
    public void testNotOverride(){
    }

}

        /*
    child继承了 parent 的 AInherited 注解
    BNotInterited因为没有@Inherited声明，不能被继承
     */
       /* class Child extends Parent{

            @Override
            public void testOverride() {
                super.testOverride();
            }
        }*/
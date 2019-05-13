package com.example.totallearn.designmode.decoratormode.example2;

import com.example.totallearn.utils.LogUtil;

/**
 * Created by pateo on 19-1-22.
 * 在《恶魔战士》中，游戏角色“莫莉卡·安斯兰”的原身是一个可爱少女，但当她变身时，会变成头顶及背部延伸出蝙蝠状飞翼的女妖，
 * 当然她还可以变为穿着漂亮外衣的少女。这些都可用装饰模式来实现，在本实例中的“莫莉卡”原身有 setImage(String t) 方法
 * 决定其显示方式，而其 变身“蝙蝠状女妖”和“着装少女”可以用 setChanger() 方法来改变其外观，原身与变身后的效果用 display() 方法来显示（点此下载其原身和变身后的图片），图 2 所示是其结构图。

 */

public class DecoratorTest2
{
    public static void main(String[] args)
    {
        Morrigan m0=new original();
        m0.display();
        Morrigan m1=new Succubus(m0);
        m1.display();
        Morrigan m2=new Girl(m0);
        m2.display();
    }
}
//抽象构件角色：莫莉卡
interface  Morrigan
{
    public void display();
}
//具体构件角色：原身
class original implements Morrigan
{
    private static final long serialVersionUID = 1L;
    private String t="Morrigan0.jpg";
    public original()
    {
        LogUtil.d("original","original构造");
    }
    public void setImage(String t)
    {
        this.t=t;
    }
    public void display()
    {
        LogUtil.d("original","display");
    }
}
//抽象装饰角色：变形
class Changer implements Morrigan
{
    Morrigan m;
    public Changer(Morrigan m)
    {
        this.m=m;
    }
    public void display()
    {
        m.display();
    }
}
//具体装饰角色：女妖
class Succubus extends Changer
{
    public Succubus(Morrigan m)
    {
        super(m);
    }
    public void display()
    {
        setChanger();
        super.display();
    }
    public void setChanger()
    {
        ((original) super.m).setImage("Morrigan1.jpg");
    }
}
//具体装饰角色：少女
class Girl extends Changer
{
    public Girl(Morrigan m)
    {
        super(m);
    }
    public void display()
    {
        setChanger();
        super.display();
    }
    public void setChanger()
    {
        ((original) super.m).setImage("Morrigan2.jpg");
    }
}
package designmode.buildermode;

import java.util.Random;

/**
 * 了解 这模式的套路  基本学会了
 *
 * 构建一个产品 , 设置产品的属性 , 构建者模式
 *
 *
 *
        * public void pay(String amount){
        new Charges.Builder()
        .amount(amount)
        .created("127.0.0.1")
        .subject("篮球")
        .body("NBA篮球官方提供").build().pay();
 }
 */
//支付对象
public class Charges {

    private Builder builder;

    //配合下面的构造方法 builder类中的 build()方法 , 这个看起来像
    private Charges(Builder builder){
        this.builder = builder;
    }

    //也可以在里面设置方法进行操作  进行参数的判断
    public void pay(){
        //发起支付即可
        //调用网络请求...
        System.err.println("发起支付");
        System.out.println("支付信息" + this.builder.params.id);
        //工厂模式->工厂方法、抽象工厂
        if(this.builder.params.channel.equals("alipay")){
            //支付宝支付
            System.out.println("支付宝支付");
        } else if(this.builder.params.channel.equals("wxpay")){
            //微信支付
            System.out.println("微信支付");
        }
    }

    //抽象构建者 ->  可以忽略
    public static abstract class AbsBuilder {
        public abstract <T> T build();
    }

    //参数类
    private static class ChargesParams {
        String id;
        String created;
        String channel;
        String amount;
        String subject;
        //商品的描述的信息
        String body;
    }

    //举个例子
    public static class Builder extends AbsBuilder {

        public ChargesParams params;

        public Builder() {
            this.params = new ChargesParams();
            //如果参数太多 可以提前设置一些默认值
            this.params.id = String.valueOf(new Random().nextInt(1000));
        }

        //可以看成 需要的是builder 而方法是id  这个相当于调用传入的参数是id , 就设置了id , 返回了一个builder继续的 .xx()进行调用
        // this 是指当前类 , 当前类可以进行调用类中的方法
        public Builder id(String id) {
            this.params.id = id;
            return this;
        }

        public Builder created(String created) {
            this.params.created = created;
            return this;
        }

        public Builder amount(String amount){
            this.params.amount = amount;
            return this;
        }

        public Builder channel(String channel) {
            this.params.channel = channel;
            return this;
        }

        public Builder subject(String subject) {
            this.params.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.params.body = body;
            return this;
        }

        /* 正常返回 就是一个T 先理解泛型
        @Override
        public <T> T build() {
            return
        }*/

        @Override
        public Charges build() {
            return new Charges(this);//这里在传的时候将 build 自己传进去了 , 因为她的构造方法中有将build传进去的方法
        }

    }
}

package designmode.prototypemode.demo4;

/**
 * 作者: Dream on 2018/1/7 22:31
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public abstract class AbsOrder implements IOrder {

    @Override
    protected AbsOrder clone() throws CloneNotSupportedException {
        return (AbsOrder) super.clone();
    }

}

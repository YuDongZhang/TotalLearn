package com.example.totallearn.designmode.prototypemode.demo4;

/**
 * 作者: Dream on 2018/1/7 21:46
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//订单接口->继承IOrderClonable
public interface IOrder extends Cloneable {

    /**
     * 设置订单数量
     * @param number
     */
    void setOrderNumber(int number);

    /**
     * 得到订单数量
     * @return
     */
    int getOrderNumber();

}

package com.example.totallearn.designmode.prototypemode.demo4;


import java.util.ArrayList;
import java.util.List;

/**
 * 作者: Dream on 2018/1/7 21:52
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//拆分订单
public class OrderService {

    //设计模式->代码重构
    public static List<AbsOrder> getOrder(AbsOrder order) {
        List<AbsOrder> orderList = new ArrayList<AbsOrder>();
        AbsOrder newOrder = null;
        try {
            while (order.getOrderNumber() > 200) {
                newOrder = order.clone();
                newOrder.setOrderNumber(200);
                orderList.add(newOrder);
                order.setOrderNumber(order.getOrderNumber() - 200);
            }
            orderList.add(order);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return orderList;
    }

}

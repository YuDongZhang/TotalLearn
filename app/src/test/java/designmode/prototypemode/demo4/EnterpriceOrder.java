package designmode.prototypemode.demo4;


/**
 * 作者: Dream on 2018/1/7 21:47
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//企业订单
public class EnterpriceOrder extends AbsOrder {

    private String orderName;
    private String orderCompany;
    private int orderNumber;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderCompany() {
        return orderCompany;
    }

    public void setOrderCompany(String orderCompany) {
        this.orderCompany = orderCompany;
    }

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "EnterpriceOrder{" +
                "orderName='" + orderName + '\'' +
                ", orderCompany='" + orderCompany + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}

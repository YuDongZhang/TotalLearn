package designmode.prototypemode.demo4;


/**
 * 作者: Dream on 2018/1/7 21:50
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class PersonalOrder extends AbsOrder {

    private String orderName;
    private int orderNumber;
    private User user;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }


    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PersonalOrder{" +
                "orderName='" + orderName + '\'' +
                ", orderNumber=" + orderNumber +
                ", user=" + user +
                '}';
    }

    @Override
    protected PersonalOrder clone() throws CloneNotSupportedException {
        PersonalOrder order = (PersonalOrder) super.clone();
        //方式一
//        User user = new User();
//        user.setAge(user.getAge());
//        user.setUserName(user.getUserName());
//        order.setUser(user);
        //方式二
        order.setUser(user.clone());
        return order;
    }
}

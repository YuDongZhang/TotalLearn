package designmode.prototypemode.demo4;

/**
 * 作者: Dream on 2018/1/7 22:40
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class User implements Cloneable{

    private String userName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }
}

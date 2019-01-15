package com.example.totallearn.designmode.abstractfactorymode;

import com.example.totallearn.utils.Log;

public class FarmTest {

    public FarmTest() {

        Farm f;
        Animal a;
        Plant p;
        f = new SGfarm(); //更换具体工厂就可以创造不同的东西
        a = f.newAnimal();
        p = f.newPlant();
        a.show();
        p.show();
    }



    public static void main(String[] args) {
        try {
            Farm f;
            Animal a;
            Plant p;
            f = new SGfarm();
            a = f.newAnimal();
            p = f.newPlant();
            a.show();
            p.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//抽象产品：动物类
interface Animal {
    public void show();
}

//具体产品：马类
class Horse implements Animal {

    public Horse() {
        Log.d("Horse", "Horse 构造方法");
    }

    public void show() {
        Log.d("Horse", "Horse.show");
    }
}

//具体产品：牛类
class Cattle implements Animal {


    public Cattle() {
        Log.d("Cattle", "Cattle 构造");
    }

    public void show() {
        Log.d("Cattle", "Cattle.show");
    }
}

//抽象产品：植物类
interface Plant {
    public void show();
}

//具体产品：水果类
class Fruitage implements Plant {


    public Fruitage() {
        Log.d("Fruitage", "Fruitage.构造");
    }

    public void show() {
        Log.d("Fruitage", "Fruitage.show");
    }
}

//具体产品：蔬菜类
class Vegetables implements Plant {

    public Vegetables() {
        Log.d("Vegetables", "Vegetables.构造");
    }

    public void show() {
        Log.d("Vegetables", "Vegetables.show");
    }
}

//抽象工厂：农场类
interface Farm {
    public Animal newAnimal();

    public Plant newPlant();
}

//具体工厂：韶关农场类
class SGfarm implements Farm {
    public Animal newAnimal() {
        System.out.println("新牛出生！");
        return new Cattle();
    }

    public Plant newPlant() {
        System.out.println("蔬菜长成！");
        return new Vegetables();
    }
}

//具体工厂：上饶农场类
class SRfarm implements Farm {
    public Animal newAnimal() {
        System.out.println("新马出生！");
        return new Horse();
    }

    public Plant newPlant() {
        System.out.println("水果长成！");
        return new Fruitage();
    }
}
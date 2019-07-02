package com.example.totallearn.dagger_learn.learn_1.cartest;

import dagger.Component;
import dagger.Module;

/**
 * Created by Administrator on 2019/6/21.
 *
 * 链接的桥梁  , 在build 过程中 将 tyre工厂类用过来了
 */
/*
@Component
public interface CarComponent {
    void injectCar(Car car);//方法没有返回值  方法名字可以任意
}
*/

@Component(modules = CarModule.class)//指明的是哪一个 module
public interface CarComponent {
    void injectCar(Car car);//方法没有返回值  方法名字可以任意
}


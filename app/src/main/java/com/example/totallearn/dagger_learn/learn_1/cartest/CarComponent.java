package com.example.totallearn.dagger_learn.learn_1.cartest;

import dagger.Component;

/**
 * Created by Administrator on 2019/6/21.
 */
@Component
public interface CarComponent {
    void injectCar(Car car);
}

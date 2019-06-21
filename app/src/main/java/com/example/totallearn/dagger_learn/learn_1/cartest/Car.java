package com.example.totallearn.dagger_learn.learn_1.cartest;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/6/21.
 *
 * https://www.jianshu.com/p/cd2c1c9f68d4
 *
 * https://www.jianshu.com/p/92f793e76654/
 */

public class Car {
    @Inject
    Tyre tyre;

    public Car() {
        DaggerCarComponent.builder().build().injectCar(this);
    }
}

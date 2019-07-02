package com.example.totallearn.dagger_learn.learn_1.cartest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/6/28.
 * 这种情况是一般碰到了 jar 中 构造方法不能使用的时候  比如 car 如果是 jar中的 你不能改了 , 通过下面的方式
 * 要在  component中进行指明  需要的module
 *
 * 跟 CarModule_ProvideCarFactory -> get() ->CarModule.provideCar() ->
 */

@Module
public class CarModule {

    @Provides
    static Car provideCar(){
        return new Car();
    }
}

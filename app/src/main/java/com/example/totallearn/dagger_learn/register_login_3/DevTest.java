package com.example.totallearn.dagger_learn.register_login_3;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Administrator on 2019/7/22.
 * 还是一个 标识的作用 , 可以 参照 @named
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DevTest {
}

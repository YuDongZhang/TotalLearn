package com.example.totallearn.dagger_learn.register_login_4;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2019/7/23.
 * 4.自定义@Scope： 5. 让其他Component依赖：详见 userClogincompone2   usercomponet6
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

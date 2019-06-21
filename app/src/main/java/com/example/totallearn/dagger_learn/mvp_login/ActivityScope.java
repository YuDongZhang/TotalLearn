package com.example.totallearn.dagger_learn.mvp_login;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
//单词学习 scope 范围   retention保留



@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
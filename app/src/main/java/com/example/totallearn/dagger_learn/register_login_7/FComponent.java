package com.example.totallearn.dagger_learn.register_login_7;

import javax.inject.Singleton;

import dagger.Component;

//第二步
@Singleton
@Component(modules = FModule.class)
public interface FComponent {
  //需要将SubComponent 追加到被依赖的Component中
    CComponent addCComponent();
}
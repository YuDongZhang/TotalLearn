package com.example.totallearn.dagger_learn.register_login_4;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2019/7/22.
 */
@Singleton
@Component(modules = UserModule4.class)
public interface UserCLoginComponet {
    void inject(Login_4Activity activity);
}

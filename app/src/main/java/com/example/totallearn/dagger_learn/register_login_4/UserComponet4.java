package com.example.totallearn.dagger_learn.register_login_4;


import javax.inject.Singleton;

import dagger.Component;

//@Component(modules = {UserModule.class, UserModule2.class})
//@Component(modules = {UserModule2.class})
//@Component(modules = {UserModule3.class})
@Singleton
@Component(modules = {UserModule4.class})
public interface UserComponet4 {

    void inject(RegisterLogin_4Activity activity);
}
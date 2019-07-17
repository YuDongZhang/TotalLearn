package com.example.totallearn.dagger_learn.register_login.cp;

import com.example.totallearn.MainActivity;
import com.example.totallearn.dagger_learn.register_login.RegisterLoginActivity;
import com.example.totallearn.dagger_learn.register_login.md.UserModule;
import com.example.totallearn.dagger_learn.register_login.md.UserModule2;

import dagger.Component;

//@Component(modules = {UserModule.class, UserModule2.class})
@Component(modules = {UserModule2.class})
public interface UserComponet {

    void inject(RegisterLoginActivity activity);
}
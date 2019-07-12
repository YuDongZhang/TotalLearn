package com.example.totallearn.dagger_learn.register_login.cp;

import com.example.totallearn.MainActivity;
import com.example.totallearn.dagger_learn.register_login.RegisterLoginActivity;
import com.example.totallearn.dagger_learn.register_login.md.UserModule;

import dagger.Component;

@Component(modules = {UserModule.class})
public interface UserComponet {

    void inject(RegisterLoginActivity activity);
}
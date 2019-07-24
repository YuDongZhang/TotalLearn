package com.example.totallearn.dagger_learn.register_login_7;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//第一步
@Module
public class FModule {

    @Singleton
    @Provides
    public User provideUser() {
        return new User();
    }
}
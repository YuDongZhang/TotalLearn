package com.example.totallearn.dagger_learn.register_login_7;

import com.example.totallearn.dagger_learn.register_login.UserStroe;
import com.example.totallearn.dagger_learn.register_login_4.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class CModule {

    @Per_7Activity
    @Provides
    public UserStroe7 provideUser(User user) {
        return new UserStroe7(user);
    }
}
package com.example.totallearn.dagger_learn.register_login.md;

import com.example.totallearn.dagger_learn.register_login.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    public UserModule() {

    }

    @Provides
    public ApiService provideApiService() {

        return new ApiService();
    }
}
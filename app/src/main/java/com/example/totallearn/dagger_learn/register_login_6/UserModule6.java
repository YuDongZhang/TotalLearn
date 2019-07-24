package com.example.totallearn.dagger_learn.register_login_6;

import android.content.Context;

import com.example.totallearn.dagger_learn.register_login_4.ApiService4;
import com.example.totallearn.dagger_learn.register_login_4.PerActivity;
import com.example.totallearn.dagger_learn.register_login_4.UserManager4;
import com.example.totallearn.dagger_learn.register_login_4.UserStroe4;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
调用处new UserManager(this);但是你注意看，我们只是需要一个context就要将UserManager和MainActivity中的代码都要改，
要是在实际项目中，需要改更多的参数呢？所以我们来看下用Dagger4该如何解决。这里我把需求再复杂话一些。首先我们还是先从
module来看：
 这里我们将例子复杂一下假设ApiService需要一个context，userStroe需要一个url。我们就可以这样写

 这里为什么我们可以直接引用参数参数中的context和url呢？因为我们提供了providerUrl()和provideContext()所以可以直接使用)
 */
@Module
public class UserModule6 {

    Context  context;

    public UserModule6(Context context) {
        this.context = context;
    }

    @PerActivity
    @Provides
    public ApiService6 provideApiService6(){
        ApiService6 apiService6 = new ApiService6(context);
        return apiService6;
    }


    @Provides
    public String providerUrl(){
        return "www.baidu.com";
    }

    @Provides
    public Context provideContext() {
        return context;
    }


}
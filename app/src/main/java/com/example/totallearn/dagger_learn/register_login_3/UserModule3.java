package com.example.totallearn.dagger_learn.register_login_3;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/*
调用处new UserManager(this);但是你注意看，我们只是需要一个context就要将UserManager和MainActivity中的代码都要改，
要是在实际项目中，需要改更多的参数呢？所以我们来看下用Dagger3该如何解决。这里我把需求再复杂话一些。首先我们还是先从
module来看：
 这里我们将例子复杂一下假设ApiService需要一个context，userStroe需要一个url。我们就可以这样写

 这里为什么我们可以直接引用参数参数中的context和url呢？因为我们提供了providerUrl()和provideContext()所以可以直接使用)
 */
@Module
public class UserModule3 {

    Context  context;

    public UserModule3(Context context) {
        this.context = context;
    }

    @Provides
    @Named("dev")//针对里面的2个构造方法
    public ApiService3 provideApiServiceDev(String url) {
        ApiService3 apiService3 = new ApiService3(url);
        return apiService3;
    }

    @Provides
    @Named("release")
    public ApiService3 provideApiServiceRelease(){
        ApiService3 apiService3 = new ApiService3(context);
        return apiService3;
    }

    //可以点进去查看 @named 的方法 ,

    @Provides
    @DevTest
    public ApiService3 privideApiserviceDevTest(){
        ApiService3 apiService3 = new ApiService3(context);
        return apiService3;
    }

    @Provides
    @ReleaseTest
    public ApiService3 privideApiserviceReleaseTest(){
        ApiService3 apiService3 = new ApiService3(context);
        return apiService3;
    }



    @Provides
    public String providerUrl(){
        return "www.baidu.com";
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public UserManager3 provideUserManager(UserStroe3 userStroe3, ApiService3 apiService3) {
        return new UserManager3(userStroe3, apiService3);
    }
}
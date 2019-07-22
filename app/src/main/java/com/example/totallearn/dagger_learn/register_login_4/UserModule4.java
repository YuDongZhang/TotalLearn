package com.example.totallearn.dagger_learn.register_login_4;

import android.content.Context;

import javax.inject.Named;
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
public class UserModule4 {

    Context  context;

    public UserModule4(Context context) {
        this.context = context;
    }

    //首先在Module中将创建实例的方法加上@Singleton  module 的 provide 方法使用了 scope ，那么 component 就
    // 必须使用同一个注解
//    @Singleton 的生命周期依附于component，同一个module被不同的@Component依赖结果也不一样
//    @Singleton分为Activity级别单例生命周期和全局的生命周期单例
//    这里第一点注意我们通过上面的事例比较容易理解，那么第二点是什么意思呢？这句话的意思在于@Singleton 的生命周期
// 依附于component。那么实际测试下。我们在创建一个LoginAcyivity，然后MainActivity创建对象后直接跳转LoginAcyivity。
    @Singleton
    @Provides
    public ApiService4 provideApiService4(){
        ApiService4 apiService4 = new ApiService4(context);
        return apiService4;
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
    public UserManager4 provideUserManager(UserStroe4 userStroe4, ApiService4 apiService4) {
        return new UserManager4(userStroe4, apiService4);
    }
}
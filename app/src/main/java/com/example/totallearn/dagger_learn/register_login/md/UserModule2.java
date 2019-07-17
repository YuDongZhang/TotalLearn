package com.example.totallearn.dagger_learn.register_login.md;

import android.content.Context;

import com.example.totallearn.dagger_learn.register_login.ApiService;
import com.example.totallearn.dagger_learn.register_login.UserManager2;
import com.example.totallearn.dagger_learn.register_login.UserStroe;

import dagger.Module;
import dagger.Provides;
/*
调用处new UserManager(this);但是你注意看，我们只是需要一个context就要将UserManager和MainActivity中的代码都要改，
要是在实际项目中，需要改更多的参数呢？所以我们来看下用Dagger2该如何解决。这里我把需求再复杂话一些。首先我们还是先从
module来看：
 这里我们将例子复杂一下假设ApiService需要一个context，userStroe需要一个url。我们就可以这样写
 */
@Module
public class UserModule2 {

    Context  context;

    public UserModule2(Context context) {
        this.context = context;
    }

    @Provides
    public ApiService provideApiService() {

        return new ApiService();
    }

    @Provides
    public String providerUrl(){
        return "www.baidu.com";
    }

    @Provides
    public UserManager2 provideUserManager(UserStroe userStroe,ApiService apiService) {
        return new UserManager2(userStroe, apiService);
    }
}
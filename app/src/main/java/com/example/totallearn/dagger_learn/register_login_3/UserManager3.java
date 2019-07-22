package com.example.totallearn.dagger_learn.register_login_3;

import javax.inject.Inject;

public class UserManager3 {

    UserStroe3 mUserStroe3;
    ApiService3 mApiService3;

    @Inject
    public UserManager3(UserStroe3 mUserStroe3, ApiService3 mApiService3) {
        this.mUserStroe3 = mUserStroe3;
        this.mApiService3 = mApiService3;
    }

    public void register3() {
        mApiService3.register3();
        mUserStroe3.login3();
    }


}


/*
然后我们在MainActivity中去调用UserManager中的register方法。这么一个看好像是没什么问题，但是如果我们mApiService.register();
需要一个Context参数怎么办？你可能会这么该：

public class UserManager {

    UserStroe mUserStroe;
    ApiService mApiService;

    public UserManager(Context context) {
        mApiService = new ApiService(context);
        mUserStroe = new UserStroe();
    }

    public void register() {
        mApiService.register();
        mUserStroe.login();
    }
}

调用处new UserManager(this);但是你注意看，我们只是需要一个context就要将UserManager和MainActivity中的代码都要改，
要是在实际项目中，需要改更多的参数呢？所以我们来看下用

Dagger3该如何解决。这里我把需求再复杂话一些。首先我们还是先从module来看：
 这里我们将例子复杂一下假设ApiService需要一个context，userStroe需要一个url。我们就可以这样写

 @Module
public class UserModule {

    Context context;

    public UserModule(Context context) {
        this.context = context;
    }

    @Provides
    public ApiService provideApiService() {
        return new ApiService(context);
    }
    @Provides
    public String providerUrl() {
        return "www.baidu.com";
    }

    @Provides
    public UserManager provideUserManager(ApiService apiService, UserStroe userStroe) {
        return new UserManager(userStroe, apiService);
    }
}




 */
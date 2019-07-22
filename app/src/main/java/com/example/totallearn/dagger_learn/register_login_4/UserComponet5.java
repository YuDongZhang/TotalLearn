package com.example.totallearn.dagger_learn.register_login_4;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.totallearn.MainActivity;
import com.example.totallearn.R;
import com.example.totallearn.dagger_learn.register_login.ApiService;
import com.example.totallearn.dagger_learn.register_login.cp.DaggerUserComponet;
import com.example.totallearn.dagger_learn.register_login.md.UserModule;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

//@Component(modules = {UserModule.class, UserModule2.class})
//@Component(modules = {UserModule2.class})
//@Component(modules = {UserModule3.class})
@Singleton
@Component(modules = {UserModule4.class})
public interface UserComponet5 {

    void inject(RegisterLogin_4Activity activity);
}

/*

module 的 provide 方法使用了 scope ，那么 component 就必须使用同一个注解
@Singleton 的生命周期依附于component，同一个module被不同的@Component依赖结果也不一样
@Singleton分为Activity级别单例生命周期和全局的生命周期单例
 这里第一点注意我们通过上面的事例比较容易理解，那么第二点是什么意思呢？这句话的意思在于@Singleton 的生命周期依附于
 component。那么实际测试下。我们在创建一个LoginAcyivity，然后MainActivity创建对象后直接跳转LoginAcyivity。
 并创建LogingConponent如下：
@Singleton
@Component(modules = {UserModule.class})
public interface UserComponet {

    void inject(MainActivity activity);
    void inject(LoginActivity activity);
}

public class LoginActivity extends AppCompatActivity {

    @Inject
    ApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserComponet userComponet = DaggerUserComponet.builder().userModule(new UserModule(this)).build();
        userComponet.inject(this);
        Log.d("TAG", "LoginActivity-->mApiService : "+mApiService);
        Log.d("TAG", "LoginActivity-->UserComponet : "+userComponet);
    }
}


我们发现一个问题 , 只能在对应的 activity 才能实现单例 , 想要做全局的 需要自定义


 */
package com.example.totallearn.dagger_learn.register_login_6;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2019/7/23
 * 2. 创建全局AppComponent：  第

 */

@Singleton
@Component(modules = AppMoudle.class)
public interface AppComponent {
    /**
     * 全局单例。所以不用Inject Activity
     *
     * @return 向下返回ApiService实例
     */
    ApiService6 getApiService6();
}

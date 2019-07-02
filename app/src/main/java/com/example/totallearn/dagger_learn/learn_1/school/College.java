package com.example.totallearn.dagger_learn.learn_1.school;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/6/28.
 */

public class College {
    @Inject
    Student student;

    public College() {
        //在这个build方法中调用了成员的注入器 , 成员注入器create中, 调用 student_freate
        //inject方法中 collegeMembersInjector.injectMembers(college)
        //跟进 injectMembers 找到他的父类的方法 , 发现 studentProvider.get() , 这个get 方法是工厂模式中的创建的方法
        //
        DaggerCollegeComponent
                .builder()
                //.CollegeModule()
                .collegeModule(new CollegeModule())//你想要这个写出来就还是得去指定
                .build()
                .inject(this);

        /*
        跟 DaggerCollegeComponent 构造函数  initialize(builder) -->
         */
    }



}

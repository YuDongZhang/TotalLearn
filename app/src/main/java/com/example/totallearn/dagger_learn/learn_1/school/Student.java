package com.example.totallearn.dagger_learn.learn_1.school;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/6/28.
 */

public class Student {
    private int id;
    private String name;

    @Inject//标签加在这里说明要通过这样的方法来完成构建的
    public Student() {
        System.out.print("student 创建了");
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

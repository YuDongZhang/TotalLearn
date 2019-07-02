package com.example.totallearn.dagger_learn.learn_1.school;

import dagger.Component;

/**
 * Created by Administrator on 2019/6/28.
 *
 */
@Component(modules = CollegeModule.class)
public interface CollegeComponent {
    void inject(College college);
}

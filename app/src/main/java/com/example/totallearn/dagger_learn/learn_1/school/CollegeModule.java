package com.example.totallearn.dagger_learn.learn_1.school;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/6/28.
 */

@Module
public class CollegeModule {

    @Provides
    ClassRoom provideClassRoom(){
        return new ClassRoom();
    }

    @Provides
    Student provideStudent(ClassRoom classRoom){
        return new Student();
    }
}

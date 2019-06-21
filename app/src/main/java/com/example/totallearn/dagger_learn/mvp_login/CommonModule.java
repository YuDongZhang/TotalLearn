package com.example.totallearn.dagger_learn.mvp_login;

import dagger.Module;
import dagger.Provides;
//单词学习  module 模块  common共同  Provides提供


@Module
 public class CommonModule{

    private ICommonView iView;
    public CommonModule(ICommonView iView){
        this.iView = iView;
    }


    @Provides
    @ActivityScope
    public ICommonView provideIcommonView(){
        return this.iView;
    }
  
 }
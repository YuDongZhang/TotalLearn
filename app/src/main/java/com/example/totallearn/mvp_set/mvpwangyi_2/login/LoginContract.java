package com.example.totallearn.mvp_set.mvpwangyi_2.login;

import com.example.totallearn.mvp_set.mvpwangyi_2.bean.BaseEntity;

// 将Model层、View层、Presenter层协商的共同业务，封装成接口
// 契约、合同
public interface LoginContract {

    interface Model {
        // Model层子类完成方法的具体实现----------------2
        void executeLogin(String name, String pwd) throws Exception;//2.p让m做的,m完成之后会告诉p,"responseResult(T t)"
    }

    interface View<T extends BaseEntity> {
        // 真实的项目中，请求结果往往是以javabean--------------4
        void handlerResult(T t);//4.收到p告知的来刷新
    }

    interface Presenter<T extends BaseEntity> {
        // 登录请求（接收到View层指令，可以自己做，也可以让Model层去执行）-----------1
        void requestLogin(String name, String pwd); //1.m让p去做,p让m去做

        // 结果响应（接收到Model层处理的结果，通知View层刷新）---------------3
        void responseResult(T t);//3.m告知p要做的,p告知v刷新,所以需要t泛型
    }
}

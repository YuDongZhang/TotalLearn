package com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRequestNetwork {

    // 请求注册 功能  todo 耗时操作 ---> OkHttp
    public Observable<RegisterResponse> registerAction(@Body RegisterRequest registerRequest);

    // 请求登录 功能 todo 耗时操作 ---> OKHttp
    public Observable<LoginResponse> loginAction(@Body LoginRequest loginRequest);

    @POST("teacher/userList")
    Observable<TestData> testAction(@Body TestRequest testRequest);

    @FormUrlEncoded
    @POST("teacher/userList")
    Observable<TestData> myAction(@FieldMap Map<String,Object> params);


    @POST("teacher/userList")
    Observable<TestData> quest5Action(@Body JSONObject params);

    @FormUrlEncoded
    @POST("teacher/userList")
    Observable<TestData> quest6Action(@Field("userFlow") String userFlow,@Field("deptFlow") String deptFlow,
    @Field("pageIndex") int index,@Field("pageSize") int pageSize);
}

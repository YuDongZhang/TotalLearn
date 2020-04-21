package com.example.totallearn.fragmentset.frag02;

import com.example.totallearn.bean.UserEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TestApi{

        @POST("gzdh/login")
      //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        /*Call<UserEntity> getPostData2(@Query("userPasswd") String nameStr,
                                      @Query("userCode") String sexSt,
                                      @Query("test") String test*/
        Call<UserEntity> getPostData2(@QueryMap(encoded = true )Map<String,String> map
        );
    }
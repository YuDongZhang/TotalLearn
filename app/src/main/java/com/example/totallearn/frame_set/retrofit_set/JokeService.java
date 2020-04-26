package com.example.totallearn.frame_set.retrofit_set;

import com.example.totallearn.fragmentset.frag04.f4entity.JokeEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


//1 . 创建接口
public interface JokeService {
    //https://api.apiopen.top/getJoke
    @GET("getJoke")
    Call<JokeEntity> getJokeCall(@Query("page") int page, @Query("count") int count, @Query("type") String type);

    @POST("getJoke")
    Call<JokeEntity> postJokeCall(@Query("page") int page, @Query("count") int count, @Query("type") String type);

    @POST("getJoke")
    Observable<JokeEntity> RxJokeCall(@Query("page") int page, @Query("count") int count, @Query("type") String type);

}
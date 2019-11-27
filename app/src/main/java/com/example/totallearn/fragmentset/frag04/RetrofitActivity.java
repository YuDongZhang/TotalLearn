package com.example.totallearn.fragmentset.frag04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.fragmentset.frag04.f4entity.JokeEntity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


    }

    //1 . 创建接口
    public interface  JokeService{
        //https://api.apiopen.top/getJoke
        @GET("getJoke")
        Call<JokeEntity> getCall();
    }


    //2 . 创建retrofit实例
    private void CreateRt(){
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apiopen.top/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //3 . 创建网络请求接口实例
        JokeService request = retrofit.create(JokeService.class);
        //对请求进行封装
        Call<JokeEntity> call = request.getCall();

        //4 . 发起异步网络请求
        call.enqueue(new Callback<JokeEntity>() {
            @Override
            public void onResponse(Call<JokeEntity> call, Response<JokeEntity> response) {

            }

            @Override
            public void onFailure(Call<JokeEntity> call, Throwable t) {

            }
        });



        //4 . 同步网络请求
       /* try {
            Response<JokeEntity> response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


}

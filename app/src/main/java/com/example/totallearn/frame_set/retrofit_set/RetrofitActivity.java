package com.example.totallearn.frame_set.retrofit_set;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.JsonUtils;
import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.fragmentset.frag04.f4entity.JokeEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.content)
    TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.r_1, R.id.r_2, R.id.r_3, R.id.r_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.r_1:
                retrofitGetAndPost();
                break;

            case R.id.r_2:
                rxjavaAndRetrofit();
                break;

            case R.id.r_3:

                break;

            case R.id.r_4:

                break;
        }
    }

    // 既然是步骤 , 哪它就是一步一步的引出来的
    //1 . 创建接口
    //2 . 创建retrofit实例
    private void retrofitGetAndPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apiopen.top/")
                .addConverterFactory(GsonConverterFactory.create())//converter 转换器 , 转为gson对象  Converter是对于Call<T>中T的转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//CallAdapter则可以对Call转换，这样的话Call<T>中的Call也是可以被替换的
                .build();

        //3 . 创建网络请求接口实例
        JokeService request = retrofit.create(JokeService.class);
        //4 , 创建 call 实例
        //  Call<JokeEntity> call = request.getJokeCall(1,10,"video");
        Call<JokeEntity> call = request.postJokeCall(1, 10, "video");
        //5 . 发起异步网络请求
        call.enqueue(new Callback<JokeEntity>() {
            @Override
            public void onResponse(Call<JokeEntity> call, Response<JokeEntity> response) {
                contentTv.setText(response.body().toString());
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

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            try {
                String text = URLDecoder.decode(message, "utf-8");

                Log.d(TAG, JsonUtils.formatJson(text));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e(TAG, "log: " + message);
            }
        }
    });


    private void rxjavaAndRetrofit() {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 四个级别：NONE,BASIC,HEADER,BODY
        // BASEIC:请求/响应行
        // HEADER:请求/响应行 + 头
        // BODY:请求/响应航 + 头 + 体

        // OkHttpClient mClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();//这个是添加日志拦截器, 网上说的添加网络拦截器
        OkHttpClient mClient = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();//这个是添加日志拦截器, 网上说的添加网络拦截器
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apiopen.top/")
                .addConverterFactory(GsonConverterFactory.create())//converter 转换器 , 转为gson对象  Converter是对于Call<T>中T的转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//CallAdapter则可以对Call转换，这样的话Call<T>中的Call也是可以被替换的
                .client(mClient)
                .build();

        //3 . 创建网络请求接口实例
        JokeService service = retrofit.create(JokeService.class);
        service.RxJokeCall(1, 10, "video")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JokeEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JokeEntity jokeEntity) {
                        contentTv.setText(jokeEntity.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}

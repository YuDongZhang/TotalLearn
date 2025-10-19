package com.example.totallearn.fragmentset.frag02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.example.totallearn.R;
import com.example.totallearn.bean.UserEntity;
import com.example.totallearn.dagger_learn.mvp_login.LoginDaggerActivity;
import com.example.totallearn.fragmentset.adapter.Frag09Adapter;
import com.example.totallearn.new_view_test.DrawerNavigationActivity;
import com.example.totallearn.new_view_test.DrawerTestActivity;
import com.example.totallearn.time_test.TimeActivity;
import com.example.totallearn.utils.MyLogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment02 extends Fragment {

    public static final String TAG = Fragment02.class.getSimpleName();

    private Retrofit retrofit;

    public String[] data = {
            "0.抽屉布局",
            "1.抽屉导航布局",
            "2.dagger 登录",
            "3.倒计时布局",
            "4.测试 retrofit",
            "5.测试 okgo",
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_02, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        Frag09Adapter adapter = new Frag09Adapter(data);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Frag09Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        Intent intent = new Intent(getActivity(), DrawerTestActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), DrawerNavigationActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), LoginDaggerActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), TimeActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        testRetrofit();
                        break;
                    case 5:
                        testOkgo();
                        break;
                }
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        add();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    private void testRetrofit() {
        final Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userPasswd", "@teh666");
        paramsMap.put("userCode", "130923199212011021");
        Call<UserEntity> call = retrofit.create(TestApi.class).getPostData2(paramsMap);
        call.enqueue(new retrofit2.Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, retrofit2.Response<UserEntity> response) {
                LogUtils.d("成功" + response.message());
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                LogUtils.d("失败");
            }
        });
    }


    //自定义OkHttpClient
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    public void add() {
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                //获得请求信息，此处如有需要可以添加headers信息
                okhttp3.Request request = chain.request();
                //添加Cookie信息
                request.newBuilder().addHeader("Cookie", "aaaa");
                //打印请求信息
                System.out.println("url:" + request.url());
                System.out.println("method:" + request.method());
                System.out.println("request-body:" + request.body());

                //记录请求耗时
                long startNs = System.nanoTime();
                okhttp3.Response response;
                try {
                    //发送请求，获得相应，
                    response = chain.proceed(request);
                } catch (Exception e) {
                    throw e;
                }
                long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
                //打印请求耗时
                System.out.println("耗时:" + tookMs + "ms");
                //使用response获得headers(),可以更新本地Cookie。
                System.out.println("headers==========");
                Headers headers = response.headers();
                System.out.println(headers.toString());
                //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
                ResponseBody responseBody = response.body();
                //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                //获得返回的数据
                Buffer buffer = source.buffer();
                //使用前clone()下，避免直接消耗
                System.out.println("response:" + buffer.clone().readString(Charset.forName("UTF-8")));
                return response;
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://47.99.88.134:8080/pdapp/res/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())//添加了定义的拦截器
                .build();
    }


    private void testOkgo() {
        MyLogUtil.d(TAG, "点击了");
        OkGo.<File>get("http://192.168.2.235:8080/pdapp/res/gzdh/student/downFile?fileFlow=ee565c6033c34a2d8259f6c582c980b0").tag(this)
                //也可以不指定名字 在 filecallback中不传值 , 也可以拿到名字, 把路径返回出去就可以了
                .execute(new FileCallback("/storage/emulated/0/download/", "abc.xls") {
                    @Override
                    public void onSuccess(Response<File> response) {
                        MyLogUtil.d(TAG, response.body().getAbsolutePath());
                        MyLogUtil.d(TAG, response.body().getName());
                        MyLogUtil.d(TAG, response.body().getPath());
                    }
                });
    }


}

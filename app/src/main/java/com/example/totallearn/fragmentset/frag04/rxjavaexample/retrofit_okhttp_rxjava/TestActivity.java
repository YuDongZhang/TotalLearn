package com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.totallearn.R;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.IRequestNetwork;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.LoginRequest;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.LoginResponse;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.MyRetrofit;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.RegisterRequest;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.RegisterResponse;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.TestData;
import com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp.TestRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Retrofit + RxJava
 * 需求：
 * 1.请求服务器注册操作
 * 2.注册完成之后，更新注册UI
 * 3.马上去登录服务器操作
 * 4.登录完成之后，更新登录的UI
 */
public class TestActivity extends AppCompatActivity {

    private final String TAG = TestActivity.class.getSimpleName();

    private TextView tv_register_ui;
    private TextView tv_login_ui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_rx);
        ButterKnife.bind(this);

        tv_register_ui = findViewById(R.id.tv_login_ui);
        tv_login_ui = findViewById(R.id.tv_login_ui);
    }

    @OnClick({R.id.bt_1, R.id.bt_2,R.id.bt_3,R.id.bt_4,R.id.bt_5,R.id.bt_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                request(null);
                break;
            case R.id.bt_2:
                request2(null);
                break;
            case R.id.bt_3:
                request3();
                break;

            case R.id.bt_4:
                request4();
                break;

            case R.id.bt_5:
                request5();
                break;

            case R.id.bt_6:
                request6();
                break;


        }
    }

    // 方法1
    public void request(View view) {
        // 分开写
        /**
         * 1.请求服务器注册操作
         * 2.注册完成之后，更新注册UI
         */
        // IRequestNetwork iRequestNetwork = MyRetrofit.createRetrofit().create(IRequestNetwork.class);

        // 1.请求服务器注册操作
        MyRetrofit.createRetrofit().create(IRequestNetwork.class) // IRequestNetwork
                // IRequestNetwork.registerAction
                .registerAction(new RegisterRequest())  // Observable<RegisterResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                // 2.注册完成之后，更新注册UI
                .subscribe(new Consumer<RegisterResponse>() { // 下游 简化版
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // 更新注册相关的所有UI
                        // .....
                    }
                });


        // 3.马上去登录服务器操作
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .loginAction(new LoginRequest())  // Observable<LoginResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程

                // 4.登录完成之后，更新登录的UI
                .subscribe(new Consumer<LoginResponse>() { // 下游 简化版
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        // 更新登录相关的所有UI
                        // .....
                    }
                });
    }

    public void request3(){
        TestRequest testRequest = new TestRequest("219bd2afa5a746d598ca969b36c85807","0a44b5df92bd4bf6abed4d30697a72db",1,10);
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .testAction(testRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestData>() {
                    @Override
                    public void accept(TestData testData) throws Exception {

                    }
                });
    }

    public void request4(){
        Map<String, Object> params = new HashMap<>();
        params.put("userFlow", "219bd2afa5a746d598ca969b36c85807");
        params.put("deptFlow", "0a44b5df92bd4bf6abed4d30697a72db");
        params.put("pageIndex", 2);
        params.put("pageSize", 10);
//        TestRequest testRequest = new TestRequest("219bd2afa5a746d598ca969b36c85807","0a44b5df92bd4bf6abed4d30697a72db",1,10);
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .myAction(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestData>() {
                    @Override
                    public void accept(TestData testData) throws Exception {

                    }
                });
    }

    public void request5()  {
//        JSONObject params = new JSONObject();
//        try {
//            params.put("userFlow", "219bd2afa5a746d598ca969b36c85807");
//            params.put("deptFlow", "0a44b5df92bd4bf6abed4d30697a72db");
//            params.put("pageIndex", 1);
//            params.put("pageSize", 10);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Map<String, Object> params = new HashMap<>();
        params.put("userFlow", "219bd2afa5a746d598ca969b36c85807");
        params.put("deptFlow", "0a44b5df92bd4bf6abed4d30697a72db");
        params.put("pageIndex", 2);
        params.put("pageSize", 10);



//        TestRequest testRequest = new TestRequest("219bd2afa5a746d598ca969b36c85807","0a44b5df92bd4bf6abed4d30697a72db",1,10);
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .quest5Action(toJsonObj(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestData>() {
                    @Override
                    public void accept(TestData testData) throws Exception {

                    }
                });
    }

    private JSONObject toJsonObj(Map<String, Object> map) {
        Iterator it = map.keySet().iterator();
        JSONObject resultJson = new JSONObject();
        while (it.hasNext()) {
            String key = (String) it.next();
            try {
                resultJson.put(key, map.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultJson;
    }



    public void request6()  {
//        JSONObject params = new JSONObject();
//        try {
//            params.put("userFlow", "219bd2afa5a746d598ca969b36c85807");
//            params.put("deptFlow", "0a44b5df92bd4bf6abed4d30697a72db");
//            params.put("pageIndex", 1);
//            params.put("pageSize", 10);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Map<String, Object> params = new HashMap<>();
        params.put("userFlow", "219bd2afa5a746d598ca969b36c85807");
        params.put("deptFlow", "0a44b5df92bd4bf6abed4d30697a72db");
        params.put("pageIndex", 2);
        params.put("pageSize", 10);



//        TestRequest testRequest = new TestRequest("219bd2afa5a746d598ca969b36c85807","0a44b5df92bd4bf6abed4d30697a72db",1,10);
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .quest6Action("219bd2afa5a746d598ca969b36c85807",
                        "0a44b5df92bd4bf6abed4d30697a72db",
                        1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestData>() {
                    @Override
                    public void accept(TestData testData) throws Exception {

                    }
                });
    }


    public RequestBody getRequestBody(HashMap<String, String> hashMap) {
        StringBuffer data = new StringBuffer();
        if (hashMap != null && hashMap.size() > 0) {
            Iterator iter = hashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                data.append(key).append("=").append(val).append("&");
            }
        }
        String jso = data.substring(0, data.length() - 1);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),jso);

        return requestBody;
    }



    private ProgressDialog progressDialog;

    public void request2(View view) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在执行中...");

        /**
         * 一行代码 实现需求
         * 需求：
         *  * 1.请求服务器注册操作
         *  * 2.注册完成之后，更新注册UI
         *  * 3.马上去登录服务器操作
         *  * 4.登录完成之后，更新登录的UI
         */
        MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                //  1.请求服务器注册操作  // todo 第二步 请求服务器 注册操作
                .registerAction(new RegisterRequest()) // Observable<RegisterResponse> 上游 被观察者 耗时操作
                .subscribeOn(Schedulers.io()) // todo 给上游分配异步线程

                .observeOn(AndroidSchedulers.mainThread()) // todo 给下游切换 主线程
                // 2.注册完成之后，更新注册UI

                /**
                 *  这样不能订阅，如果订阅了，就无法执行
                 *      3 马上去登录服务器操作
                 *      4.登录完成之后，更新登录的UI
                 *
                 *  所以我们要去学习一个 .doOnNext()，可以在不订阅的情况下，更新UI
                 */
                .doOnNext(new Consumer<RegisterResponse>() { // 简单版本的下游
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // todo 第三步 更新注册相关的所有UI
                        // 更新注册相关的所有UI
                        tv_register_ui.setText("xxx");
                        // .......
                    }
                })
                // 3.马上去登录服务器操作 -- 耗时操作
                .subscribeOn(Schedulers.io()) // todo 分配异步线程
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        // 还可以拿到 注册后的响应对象RegisterResponse
                        // 执行耗时操作
                        // 马上去登录服务器操作 -- 耗时操作
                        Observable<LoginResponse> observable = MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                                .loginAction(new LoginRequest());  // todo 第四步 马上去登录服务器操作 -- 耗时操作
                        return observable;
                    }
                })
                // 4.登录完成之后，更新登录的UI
                .observeOn(AndroidSchedulers.mainThread()) // // todo 给下游切换 主线程
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // todo 第一步
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        // 更新登录相关的所有UI
                        // todo 第五步 更新登录相关的所有UI
                        tv_login_ui.setText("xxxx");
                        // ...........
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        // todo 第六步
                        progressDialog.dismiss(); // 结束对话框 ，整个流程完成
                    }
                });
    }


}

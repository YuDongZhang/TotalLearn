package com.example.totallearn.testRoom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.totallearn.R;
import com.example.totallearn.databinding.ActivityMultiFunctionBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 多功能
 */
public class MultiFunctionActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMultiFunctionBinding binding;
    private UserDatabase mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_multi_function);
        binding.setLifecycleOwner(this);
        initListener();
    }

    private void initListener() {
//        findViewById(R.id.createDateBase).setOnClickListener(this);
        binding.createDateBase.setOnClickListener(this);
        binding.addData.setOnClickListener(this);
        binding.queryData.setOnClickListener(this);
        binding.updateData.setOnClickListener(this);
        binding.deleteData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createDateBase:
                createData();
                break;
            case R.id.addData:
                addData();
                break;
            case R.id.queryData:
                Completable.fromAction(() -> {
                            List<User> all = mUserDatabase.userDao().getAll();
                            LogUtils.d("all--->"+all.size());
                        }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {

                                },
                                throwable -> {

                                }
                        );

                break;
            case R.id.updateData:
                User userone = new User(18, "武媚娘123", 32);
                mUserDatabase.userDao().updateUser(userone);
                break;
            case R.id.deleteData:
                User user = new User();
                user.id = 12;
                mUserDatabase.userDao().deleteUser(user);
                break;
        }
    }

    //创建数据库
    private void createData() {
        //创建数据库
        String dir = getFilesDir() + "/user_db";
        mUserDatabase = Room.databaseBuilder(getApplication(),
                        UserDatabase.class, dir)
                .build();
    }

    private void addData() {
        Observable.create(emitter -> {
                    List<User> list = new ArrayList<>();
                    list.add(new User(10, "帅次次", 20));
                    list.add(new User(12, "朱元璋", 30));
                    list.add(new User(15, "赵匡胤", 40));
                    list.add(new User(18, "李世民", 50));
                    list.add(new User(19, "大傻逼", 50));

                    mUserDatabase.userDao().insertUsers(list);
                    LogUtils.d(mUserDatabase.userDao().getAll().size());
                    String result = "pdd";
                    emitter.onNext(result); // 发射成功的结果
                    emitter.onComplete(); // 请求完成
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        //result 就是对象随便写什么名字都可以
                        result -> {
                            ToastUtils.showShort(result.toString());
                        },
                        error -> {

                        }
                );
    }

}
package com.example.totallearn.fragmentset.frag05;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<T> extends ViewModel {

    private MutableLiveData<T> data = new MutableLiveData<>();

    public LiveData<T> getData() {
        return data;
    }

    protected void setData(T newData) {
        data.setValue(newData);
    }

    // 在此方法中执行初始化操作，例如设置初始数据
    protected abstract void init();

    // 在子类中执行数据加载或其他业务逻辑
    public abstract void loadData();


}

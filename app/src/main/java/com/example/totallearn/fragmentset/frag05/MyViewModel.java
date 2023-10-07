package com.example.totallearn.fragmentset.frag05;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<String>> itemList;

    public MyViewModel() {
        itemList = new MutableLiveData<>();
        // 在构造函数中初始化数据或从仓库中获取数据
        // itemList.setValue(loadDataFromRepository());
    }

    public void setItemList(List<String> list){
        itemList.setValue(list);
    }


    public LiveData<List<String>> getItemList() {
        return itemList;
    }
}

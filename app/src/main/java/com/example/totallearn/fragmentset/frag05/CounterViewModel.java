package com.example.totallearn.fragmentset.frag05;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CounterViewModel extends BaseViewModel<Integer> {
//    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
    private int count = 0;

//    public LiveData<Integer> getCountLiveData() {
//        return countLiveData;
//    }

    public void incrementCount() {
        count++;
        setData(count);
//        countLiveData.setValue(count);
    }

    @Override
    protected void init() {

    }

    @Override
    public void loadData() {

    }
}

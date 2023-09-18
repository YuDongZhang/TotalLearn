package com.example.totallearn.fragmentset.frag05;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 原生
 */
public class CounterViewModelCopy extends ViewModel {
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
    private int count = 0;

    public LiveData<Integer> getCountLiveData() {
        return countLiveData;
    }

    public void incrementCount() {
        count++;
        countLiveData.setValue(count);
    }
}

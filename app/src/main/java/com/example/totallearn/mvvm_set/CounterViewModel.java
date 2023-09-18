package com.example.totallearn.mvvm_set;

import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}

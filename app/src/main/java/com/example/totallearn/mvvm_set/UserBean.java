package com.example.totallearn.mvvm_set;

import androidx.databinding.ObservableField;

/**
 * Created by Shinelon on 2019/11/25.
 */

public class UserBean {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> sex = new ObservableField<>();

    public UserBean() {
        name.set("小明");
        sex.set("男");
    }
}

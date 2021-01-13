package com.example.totallearn.dagger_learn.register_login_4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.totallearn.R;

import javax.inject.Inject;

public class Login_4Activity extends AppCompatActivity {

    @Inject
    ApiService4 apiService4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_4);

        DaggerUserCLoginComponet.builder().userModule4(new UserModule4(this)).build().inject(this);
    }
}

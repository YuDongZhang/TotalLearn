package com.example.totallearn.fragmentset.frag03;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;
import com.example.totallearn.new_view_test.DrawerTestActivity;

public class MaterialDesignActivity extends BaseActivity {

    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        parentLinearLayout = findViewById(R.id.parent_ll);
        
        // 设置点击事件
        findViewById(R.id.tv1).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv2).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv3).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv4).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv5).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv6).setOnClickListener(this::onViewClicked);
    }

    private void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv1) {
            showSnackBar();
        } else if (id == R.id.tv2) {
            // do nothing
        } else if (id == R.id.tv3) {
            // do nothing
        } else if (id == R.id.tv4) {
            // do nothing
        } else if (id == R.id.tv5) {
            startActivity(new Intent(MaterialDesignActivity.this, DrawerTestActivity.class));
        } else if (id == R.id.tv6) {
            startActivity(new Intent(MaterialDesignActivity.this, ACTActivity.class));
        }
    }

    private void showSnackBar() {
//        Snackbar.make(parentLinearLayout, "我是snackbar", Snackbar.LENGTH_SHORT).show();
        Snackbar.make(parentLinearLayout, "我是snackbar", Snackbar.LENGTH_SHORT).setAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("我是toast");
            }
        }).setActionTextColor(Color.RED).show();
    }

}
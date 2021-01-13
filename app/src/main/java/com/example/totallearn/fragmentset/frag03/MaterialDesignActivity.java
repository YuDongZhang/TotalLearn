package com.example.totallearn.fragmentset.frag03;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.new_view_test.DrawerTestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialDesignActivity extends BaseActivity {

    @BindView(R.id.parent_ll)
    LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,R.id.tv6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                showSnackBar();
                break;
            case R.id.tv2:
                break;
            case R.id.tv3:

                break;
            case R.id.tv4:
                break;

            case R.id.tv5:
               startActivity(new Intent(MaterialDesignActivity.this, DrawerTestActivity.class));
                break;
            case R.id.tv6:
                startActivity(new Intent(MaterialDesignActivity.this, ACTActivity.class));
                break;
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
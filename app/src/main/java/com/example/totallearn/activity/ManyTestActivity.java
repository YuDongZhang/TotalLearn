package com.example.totallearn.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.totallearn.R;
import com.example.totallearn.view.WheelView;

/**
 * 多种测试
 */
public class ManyTestActivity extends AppCompatActivity {

    private WheelView wheelView;
    private Switch dividerSwitch;
    private Button changeColorButton;
    private Button changeVisibleItemsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_test);
        
        wheelView = findViewById(R.id.wheelView);
        
        // 设置初始选中项为50
        wheelView.setSelectedIndex(49);
        
        // 设置选择监听器
        wheelView.setOnClickListener(v -> {
            String selectedValue = wheelView.getSelectedItem();
            Toast.makeText(this, "选中的值: " + selectedValue, Toast.LENGTH_SHORT).show();
            Log.d("WheelView", "选中的值: " + selectedValue);
        });
        
        // 初始化控制按钮（如果布局中有的话）
        dividerSwitch = findViewById(R.id.dividerSwitch);
        changeColorButton = findViewById(R.id.changeColorButton);
        changeVisibleItemsButton = findViewById(R.id.changeVisibleItemsButton);
        
        // 设置间隔线开关监听器
        if (dividerSwitch != null) {
            dividerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    wheelView.setShowDividerLines(isChecked);
                }
            });
            dividerSwitch.setChecked(wheelView.isShowDividerLines());
        }
        
        // 设置改变颜色按钮监听器
        if (changeColorButton != null) {
            changeColorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 随机设置一个颜色
                    int color = 0xFF000000 | (int)(Math.random() * 0xFFFFFF);
                    wheelView.setDividerLineColor(color);
                }
            });
        }
        
        // 设置改变可见项目数量按钮监听器
        if (changeVisibleItemsButton != null) {
            changeVisibleItemsButton.setOnClickListener(new View.OnClickListener() {
                private int currentVisible = 5;
                @Override
                public void onClick(View v) {
                    // 在3、5、7之间切换
                    currentVisible = currentVisible == 3 ? 5 : (currentVisible == 5 ? 7 : 3);
                    wheelView.setVisibleItems(currentVisible);
                    Toast.makeText(ManyTestActivity.this, "可见项目数量: " + currentVisible, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
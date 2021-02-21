
package com.example.totallearn.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class AnotherBarActivity extends BaseActivity {

    private BarChart chart;

    List<BarEntry> list;
    List<BarEntry>list2;


    public static final int[] VORDIPLOM_COLORS = {
            Color.rgb(91, 155, 213), Color.rgb(237, 125, 49)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart);

        setTitle("AnotherBarActivity");
        chart = findViewById(R.id.chart1);

        chartTwo();



    }

    private void chartTwo(){
        list=new ArrayList<>();
        list2=new ArrayList<>();

        //为第一组添加数据
        list.add(new BarEntry(1,5));
        list.add(new BarEntry(2,8));
        list.add(new BarEntry(3,4));
        list.add(new BarEntry(4,3));
        list.add(new BarEntry(5,0));

        //为第二组添加数据
        list2.add(new BarEntry(1,6));
        list2.add(new BarEntry(2,8));
        list2.add(new BarEntry(3,5));
        list2.add(new BarEntry(4,3));
        list2.add(new BarEntry(5,0));

        BarDataSet barDataSet=new BarDataSet(list,"男");
        barDataSet.setColor(Color.RED);    //为第一组柱子设置颜色
        barDataSet.setValueTextSize(18);
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int i = (int) value;
                return String.valueOf(i);
            }
        });

        BarDataSet barDataSet2=new BarDataSet(list2,"女");
        barDataSet2.setColor(Color.BLUE);   //为第二组柱子设置颜色
        BarData barData=new BarData(barDataSet);   //加上第一组
        barData.addDataSet(barDataSet2);    //加上第二组   （多组也可以用同样的方法）
        chart.setData(barData);


        barData.setBarWidth(0.2f);//柱子的宽度
        //重点！   三个参数   分别代表   X轴起点     组与组之间的间隔      组内柱子的间隔
        barData.groupBars(1f,0.4f,0.1f);



        chart.getXAxis().setTextSize(18);//设置 x 轴字体大小

//        chart.getAxisLeft().setSpaceTop(18);

        chart.getAxisLeft().setTextSize(18);//设置 y 轴 , 字体大小

        YAxis yLeft = chart.getAxisLeft();
        yLeft.setStartAtZero(true);


        chart.getXAxis().setCenterAxisLabels(true);   //设置柱子（柱子组）居中对齐X轴上的点


        chart.getXAxis().setAxisMaximum(6);   //X轴最大数值
        chart.getXAxis().setAxisMinimum(1);   //X轴最小数值
        //X轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        chart.getXAxis().setLabelCount(5,false);
        chart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value==1){
                    return "第一周";
                }

                return super.getFormattedValue(value);
            }
        });



        chart.getDescription().setEnabled(false);    //右下角一串英文字母不显示
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);   //X轴的位置设置为下  默认为上
        chart.getAxisRight().setEnabled(false);     //右侧Y轴不显示   默认为显示
    }




    private void chartOne() {


        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(30f);

        chart.getAxisLeft().setDrawGridLines(false);


        // add a nice and smooth animation
        chart.animateY(1500);

        chart.getLegend().setEnabled(false);

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            float multi = (6 + 1);
            float val = (float) (Math.random() * multi) + multi / 3;
            values.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.5f);
            chart.setData(data);
            chart.setFitBars(true);
        }
    }


}

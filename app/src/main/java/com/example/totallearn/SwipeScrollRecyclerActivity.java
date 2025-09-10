package com.example.totallearn;

import android.os.Bundle;
import android.os.Handler;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.totallearn.databinding.ActivitySwipeScrollRecyclerBinding;
import com.example.totallearn.utils.MyLogUtil;

import java.util.ArrayList;
import java.util.List;

public class SwipeScrollRecyclerActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static final String TAG = "SSRecycler";

    private ActivitySwipeScrollRecyclerBinding binding;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwipeScrollRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addList();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new SCRAdapter(stringList));
        //binding.swipelayout.setEnabled(false);
        swipeListener();
        setScrollListener();
    }



    private void swipeListener() {
        binding.swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                       /* data.add(0, "刷新后新增的item");
                        adapter.notifyDataSetChanged();*/
                        binding.swipelayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void setScrollListener() {
        binding.scrolllayout.fling(0);
        binding.scrolllayout.scrollTo(0,0);
        binding.scrolllayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (view.getScrollY()!=0){
                    binding.swipelayout.setEnabled(false);
                }else if (view.getScrollY()==0){
                    binding.swipelayout.setEnabled(true);
                }
              //  MyLogUtil.d(TAG ,"1 ="+i+"   i1 = "+i1 +"  i2 = "+i2+" i3 = "+i3+"   y  "+view.getScrollY());
            }
        });



        binding.scrolllayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MyLogUtil.d(TAG,"y "+motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        MyLogUtil.d(TAG,"y "+motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        MyLogUtil.d(TAG,"y "+motionEvent.getY());
                        break;
                }
                return false;
            }
        });


    }


    private void addList() {
        for (int i = 0; i < 7; i++) {
            stringList.add(i + "");
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
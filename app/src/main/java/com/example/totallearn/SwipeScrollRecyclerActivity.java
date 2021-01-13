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

import com.example.totallearn.utils.MyLogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeScrollRecyclerActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
public static final String TAG = "SSRecycler";
    @BindView(R.id.up_area)
    RelativeLayout upArea;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.scrolllayout)
    ScrollView scrolllayout;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;
    @BindView(R.id.blue_area)
    RelativeLayout blueArea;
    @BindView(R.id.parent_area)
    RelativeLayout parentArea;

    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_scroll_recycler);
        ButterKnife.bind(this);
        addList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SCRAdapter(stringList));
        //swipelayout.setEnabled(false);
        swipeListener();
        setScrollListener();
    }



    private void swipeListener() {
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                       /* data.add(0, "刷新后新增的item");
                        adapter.notifyDataSetChanged();*/
                        swipelayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void setScrollListener() {
        scrolllayout.fling(0);
        scrolllayout.scrollTo(0,0);
        scrolllayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (view.getScrollY()!=0){
                    swipelayout.setEnabled(false);
                }else if (view.getScrollY()==0){
                    swipelayout.setEnabled(true);
                }
              //  MyLogUtil.d(TAG ,"1 ="+i+"   i1 = "+i1 +"  i2 = "+i2+" i3 = "+i3+"   y  "+view.getScrollY());
            }
        });



        scrolllayout.setOnTouchListener(new View.OnTouchListener() {
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

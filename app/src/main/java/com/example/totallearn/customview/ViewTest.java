package com.example.totallearn.customview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pateo on 19-2-20.
 */

public class ViewTest extends ViewGroup {
    public ViewTest(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    /**
     * dispatch 就是分发的意思  当点击事件产生后，事件首先会传递给当前的 Activity，
     * 这会调用 Activity 的 dispatchTouchEvent()
     *
     * 当返回为 true 的时候事件不往下传递了
     *
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);

    }


    /**
     *如果是继承到view的时候 ,
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    /**
     * 只是在 viewgroup 有下面这个方法
     * true 拦截
     * false 不拦截分发给下面的 view
     */
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }
}

package com.example.totallearn.fragmentset.frag09;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class ScaleBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    private FastOutLinearInInterpolator mFastOutLinearInInterpolator = new FastOutLinearInInterpolator();
    private LinearOutSlowInInterpolator mLinearOutSlowInInterpolator = new LinearOutSlowInInterpolator();

    public ScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //开始滑动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL; //垂直滚动 //指定滚动的轴线
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0 && !isRunning && child.getVisibility() == View.VISIBLE){//向下滑动，缩放隐藏控件 //判断view是否可见
            scaleHide(child);
        }else if (dyConsumed < 0 && !isRunning && child.getVisibility() == View.INVISIBLE){ //向上滑动，缩放显示控件
            scaleShow(child);
        }
    }

    private boolean isRunning;//动画是否在执行

    private void scaleShow(V child) {
        child.setVisibility(View.VISIBLE); //控件的可见不可见
        ViewCompat.animate(child)
                .scaleX(1)
                .scaleY(1)
                .setDuration(500)
                .setInterpolator(mLinearOutSlowInInterpolator)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){
                    @Override
                    public void onAnimationStart(View view) {
                        super.onAnimationStart(view);
                        isRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        isRunning = false;
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        super.onAnimationCancel(view);
                        isRunning = false;
                    }
                });
    }

    private void scaleHide(final V child) {
        ViewCompat.animate(child)
                .scaleX(0)
                .scaleY(0)
                .setDuration(500)
                .setInterpolator(mFastOutLinearInInterpolator)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){
                    @Override
                    public void onAnimationStart(View view) {
                        super.onAnimationStart(view);
                        isRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        isRunning = false;
                        child.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        super.onAnimationCancel(view);
                        isRunning = false;
                    }
                });
    }
}

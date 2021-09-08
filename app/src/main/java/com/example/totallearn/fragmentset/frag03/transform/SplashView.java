package com.example.totallearn.fragmentset.frag03.transform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.totallearn.R;

public class SplashView extends View {

    //旋转圆的画笔
    private Paint mPaint;
    //扩散圆的画笔
    private Paint mHolePaint;
    //属性动画
    private ValueAnimator mValueAnimator;

    //背景色
    private int mBackgroundColor = Color.WHITE;
    private int[] mCircleColors;

    //表示旋转圆的中心坐标
    private float mCenterX;
    private float mCenterY;
    //表示斜对角线长度的一半,扩散圆最大半径
    private float mDistance;

    //6个小球的半径
    private float mCircleRadius = 18;
    //旋转大圆的半径
    private float mRotateRadius = 90;

    //当前大圆的旋转角度
    private float mCurrentRotateAngle = 0F;
    //当前大圆的半径
    private float mCurrentRotateRadius = mRotateRadius;
    //扩散圆的半径
    private float mCurrentHoleRadius = 0F;
    //表示旋转动画的时长
    private int mRotateDuration = 1200;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);

        mCircleColors = context.getResources().getIntArray(R.array.splash_circle_colors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w * 1f / 2;
        mCenterY = h * 1f / 2;
        mDistance = (float) (Math.hypot(w, h) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mState == null) {
            mState = new RotateState();
        }
        mState.drawState(canvas);
    }

    private SplashState mState;

    //绘制的状态的类
    private abstract class SplashState {
        abstract void drawState(Canvas canvas);
    }

    //1.旋转
    private class RotateState extends SplashState {
        //角度进行旋转重新调用 ondraw 方法
        private RotateState() {
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));//旋转一周
            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {//监听动画执行的过程
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateAngle = (float) animation.getAnimatedValue();//当前旋转的角度
                    invalidate();//会使 onDraw()重新执行
                }
            });

            //监听动画执行的状态
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new MerginState();//状态的切换
                }
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制6个小球
            drawCircles(canvas);
        }
    }

    private void drawCircles(Canvas canvas) {
        float rotateAngle = (float) (Math.PI * 2 / mCircleColors.length);//一周除以小球的个数,得到2个小球的角度
        for (int i = 0; i < mCircleColors.length; i++) {
            // x = r * cos(a) + centX;
            // y = r * sin(a) + centY;
            //这里是先可以画上固定的各个点的坐标
            float angle = i * rotateAngle + mCurrentRotateAngle;//加上当前旋转的角度
            float cx = (float) (Math.cos(angle)*mCurrentRotateRadius + mCenterX);
            float cy = (float) (Math.sin(angle)*mCurrentRotateRadius + mCenterY);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
        }
    }

    private void drawBackground(Canvas canvas) {
        if (mCurrentHoleRadius > 0) {//表示进入到第三种状态
            //绘制空心圆
            float strokeWidth = mDistance - mCurrentHoleRadius;
            float radius = strokeWidth / 2 + mCurrentHoleRadius;
            mHolePaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle(mCenterX, mCenterY, radius, mHolePaint);
        } else {
            canvas.drawColor(mBackgroundColor);
        }
    }

    //2.扩散聚合
    private class MerginState extends SplashState {

        private MerginState() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);//开始之后执行到一个大圆
//            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new OvershootInterpolator(10f));//插值器
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new ExpandState();
                }
            });
            mValueAnimator.reverse();//这个反向操作
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
            drawCircles(canvas);
        }
    }

    //3.水波纹
    private class ExpandState extends SplashState {

        public ExpandState() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance);
//            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentHoleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });

            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
        }
    }

}

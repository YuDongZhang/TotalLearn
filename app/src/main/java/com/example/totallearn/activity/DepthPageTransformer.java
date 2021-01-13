package com.example.totallearn.activity;

import androidx.viewpager.widget.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {
//    private static final float MIN_SCALE = 0.70f;
//    private static final float MIN_ALPHA = 0.5f;
//
//    @Override
//    public void transformPage(View page, float position) {
//        if (position < -1 || position > 1) {
//            page.setAlpha(MIN_ALPHA);
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
//        } else if (position <= 1) { // [-1,1]
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//            if (position < 0) {
//                float scaleX = 1 + 0.1f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
//            } else {
//                float scaleX = 1 - 0.1f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
//            }
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//        }
//    }

    private static final float MIN_SCALE = 0.9f;

    @Override
    public void transformPage(View view, float position) {
        /**
         * 过滤那些 <-1 或 >1 的值，使它区于【-1，1】之间
         */
        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }
        /**
         * 判断是前一页 1 + position ，右滑 pos -> -1 变 0
         * 判断是后一页 1 - position ，左滑 pos -> 1 变 0
         */
        float tempScale = position < 0 ? 1 + position : 1 - position; // [0,1]
        float scaleValue = MIN_SCALE + tempScale * 0.1f; // [0,1]
        view.setScaleX(scaleValue);
        view.setScaleY(scaleValue);
    }
}

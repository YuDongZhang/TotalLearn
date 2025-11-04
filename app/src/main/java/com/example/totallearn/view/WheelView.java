package com.example.totallearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WheelView extends View {
    private Paint paint;
    private Paint linePaint;
    private List<String> items = new ArrayList<>();
    private int selectedIndex = 0;
    private float itemHeight = 60;
    private float scrollOffset = 0;
    private float lastY = 0;
    private boolean isScrolling = false;
    private boolean showDividerLines = true; // 是否显示间隔线
    private int visibleItems = 5; // 可见项目数量

    public WheelView(Context context) {
        super(context);
        init();
    }

    public WheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);
        
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(2);
        linePaint.setColor(0xFFCCCCCC); // 默认灰色
        
        // 添加数据 1-100
        for (int i = 1; i <= 100; i++) {
            items.add(String.valueOf(i));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        float centerY = getHeight() / 2;
        
        // 计算可见范围 - 使用用户设置的visibleItems
        int halfVisible = visibleItems / 2;
        int start = Math.max(0, selectedIndex - halfVisible);
        int end = Math.min(items.size() - 1, selectedIndex + halfVisible);
        
        // 如果visibleItems是奇数且当前显示的项目数量不足，扩展显示范围
        int currentVisibleCount = end - start + 1;
        if (currentVisibleCount < visibleItems) {
            // 尝试向下扩展
            if (end + 1 < items.size()) {
                end = Math.min(items.size() - 1, end + (visibleItems - currentVisibleCount));
            }
            // 如果向下扩展不够，尝试向上扩展
            else if (start > 0) {
                start = Math.max(0, start - (visibleItems - currentVisibleCount));
            }
        }
        
        // 绘制所有可见项目
        for (int i = start; i <= end; i++) {
            float itemY = centerY + (i - selectedIndex) * itemHeight + scrollOffset;
            
            // 只绘制在视图范围内的项目
            if (itemY < -itemHeight || itemY > getHeight() + itemHeight) {
                continue;
            }
            
            // 设置透明度，离中心越远越透明
            float distance = Math.abs(itemY - centerY);
            float alpha = Math.max(0.3f, 1 - distance / (itemHeight * (visibleItems / 2)));
            paint.setAlpha((int) (255 * alpha));
            
            // 设置大小，离中心越远越小
            float scale = Math.max(0.7f, 1 - distance / (itemHeight * (visibleItems / 2 + 1)));
            float textSize = 40 * scale;
            paint.setTextSize(textSize);
            
            canvas.drawText(items.get(i), getWidth() / 2, itemY, paint);
        }
        
        // 绘制中心线（如果启用）
        if (showDividerLines) {
            linePaint.setAlpha(255);
            canvas.drawLine(0, centerY - itemHeight / 2, getWidth(), centerY - itemHeight / 2, linePaint);
            canvas.drawLine(0, centerY + itemHeight / 2, getWidth(), centerY + itemHeight / 2, linePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                isScrolling = true;
                return true;
                
            case MotionEvent.ACTION_MOVE:
                if (isScrolling) {
                    float deltaY = event.getY() - lastY;
                    scrollOffset += deltaY;
                    
                    // 计算滚动偏移对应的项目索引变化
                    int offsetItems = (int) (scrollOffset / itemHeight);
                    if (offsetItems != 0) {
                        // 修正滚动方向：向上滑动（deltaY为负）应该增加索引
                        int newIndex = selectedIndex - offsetItems; // 注意这里是减法，修正方向
                        if (newIndex >= 0 && newIndex < items.size()) {
                            selectedIndex = newIndex;
                            scrollOffset -= offsetItems * itemHeight; // 减去已处理的偏移量
                        }
                    }
                    
                    lastY = event.getY();
                    invalidate();
                }
                return true;
                
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isScrolling) {
                    // 计算最近的选中项
                    float totalOffset = scrollOffset;
                    int offsetItems = Math.round(totalOffset / itemHeight); // 使用四舍五入
                    
                    // 修正滚动方向：向上滑动（totalOffset为负）应该增加索引
                    // 更新选中索引
                    int newIndex = selectedIndex - offsetItems; // 注意这里是减法，修正方向
                    newIndex = Math.max(0, Math.min(items.size() - 1, newIndex)); // 确保在有效范围内
                    
                    // 重置滚动状态
                    scrollOffset = 0;
                    selectedIndex = newIndex;
                    isScrolling = false;
                    invalidate();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    public String getSelectedItem() {
        if (selectedIndex >= 0 && selectedIndex < items.size()) {
            return items.get(selectedIndex);
        }
        return "";
    }

    public void setSelectedIndex(int index) {
        if (index >= 0 && index < items.size()) {
            this.selectedIndex = index;
            invalidate();
        }
    }
    
    /**
     * 设置是否显示间隔线
     * @param show 是否显示
     */
    public void setShowDividerLines(boolean show) {
        this.showDividerLines = show;
        invalidate();
    }
    
    /**
     * 获取是否显示间隔线
     * @return 是否显示间隔线
     */
    public boolean isShowDividerLines() {
        return showDividerLines;
    }
    
    /**
     * 设置间隔线颜色
     * @param color 颜色值
     */
    public void setDividerLineColor(int color) {
        linePaint.setColor(color);
        invalidate();
    }
    
    /**
     * 设置间隔线宽度
     * @param width 宽度（像素）
     */
    public void setDividerLineWidth(float width) {
        linePaint.setStrokeWidth(width);
        invalidate();
    }
    
    /**
     * 设置可见项目数量
     * @param count 可见项目数量（建议为奇数）
     */
    public void setVisibleItems(int count) {
        if (count > 0) {
            this.visibleItems = count;
            invalidate();
        }
    }
    
    /**
     * 获取可见项目数量
     * @return 可见项目数量
     */
    public int getVisibleItems() {
        return visibleItems;
    }
}
package com.example.totallearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * 自定义验证码输入控件
 * 特点：
 * 1. 看起来像一个长的EditText
 * 2. 下面有6段间隔线，都是灰色
 * 3. 随输入下面的线变成蓝色
 * 4. 6段线
 * 5. 数字在线上中间
 */
public class VerificationCodeInput extends AppCompatEditText {

    private Paint linePaint;
    private Paint textPaint;
    private Paint cursorPaint;
    private int lineColor = Color.GRAY;
    private int activeLineColor = Color.BLUE;
    private int textColor = Color.BLACK;
    private int cursorColor = Color.BLUE;
    private float lineWidth = 2f;
    private float lineHeight = 8f;
    private int codeLength = 6;
    private float textSize = 48f;
    private float textMarginTop = 16f;
    private float lineSpacing;
    private float charWidth;
    private float lineGap = 20f; // 线条之间的间隔
    private boolean showCursor = true; // 是否显示光标

    public VerificationCodeInput(Context context) {
        super(context);
        init();
    }

    public VerificationCodeInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerificationCodeInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 设置背景为透明
        setBackgroundColor(Color.TRANSPARENT);
        
        // 设置最大输入长度为6
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(codeLength)});
        
        // 设置输入类型为数字
        setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        
        // 设置光标不可见，我们将自定义绘制
        setCursorVisible(false);
        
        // 初始化画笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(lineWidth);
        
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        
        // 初始化光标画笔
        cursorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cursorPaint.setColor(cursorColor);
        cursorPaint.setStrokeWidth(lineWidth);
        
        // 计算字符宽度
        charWidth = textPaint.measureText("8");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 不调用super.onDraw()，避免默认文本绘制与我们的自定义绘制重叠
        
        // 计算每条线的位置和间距
        float totalWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        float segmentWidth = (totalWidth - (codeLength - 1) * lineGap) / codeLength;
        lineSpacing = segmentWidth + lineGap;
        
        // 绘制文本和下划线
        String text = getText().toString();
        for (int i = 0; i < codeLength; i++) {
            float x = getPaddingLeft() + segmentWidth * (i + 0.5f) + lineGap * i;
            
            // 绘制下划线
            float lineStartX = x - segmentWidth / 2;
            float lineEndX = x + segmentWidth / 2;
            float lineY = getHeight() / 2f + 20f; // 调整线条位置，向下偏移20像素
            
            // 如果当前字符位置有输入，则线变为蓝色，否则为灰色
            if (i < text.length()) {
                linePaint.setColor(activeLineColor);
            } else {
                linePaint.setColor(lineColor);
            }
            
            // 确保线条始终可见，增加线条宽度
            linePaint.setStrokeWidth(lineWidth * 2);
            canvas.drawLine(lineStartX, lineY, lineEndX, lineY, linePaint);
            
            // 绘制文本 - 数字显示在线条上方
            if (i < text.length()) {
                // 计算文本基线，使数字垂直居中于线条
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float baseline = lineY - (fontMetrics.ascent + fontMetrics.descent) / 2 - 30f; // 向上偏移10像素
                // 只绘制当前位置对应的字符
                canvas.drawText(String.valueOf(text.charAt(i)), x, baseline, textPaint);
            }
            
            // 绘制光标 - 在下一个空位显示
            if (i == text.length() && i < codeLength && isFocused() && showCursor) {
                // 绘制光标，高度与文本大小一致
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                float cursorTop = lineY + fontMetrics.top - 10f;
                float cursorBottom = lineY + fontMetrics.bottom - 10f;
                cursorPaint.setStrokeWidth(lineWidth * 2);
                canvas.drawLine(x, cursorTop, x, cursorBottom, cursorPaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
        // 确保高度足够容纳文本和下划线
        int desiredHeight = (int) (textSize + textMarginTop + lineHeight + getPaddingTop() + getPaddingBottom() + 20f);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            height = Math.min(desiredHeight, height);
        }
        
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    /**
     * 设置输入完成的监听器
     */
    public void setOnCodeCompleteListener(OnCodeCompleteListener listener) {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                if (s.length() == codeLength && listener != null) {
                    listener.onCodeComplete(s.toString());
                }
            }
        });
    }

    /**
     * 输入完成监听器接口
     */
    public interface OnCodeCompleteListener {
        void onCodeComplete(String code);
    }
}
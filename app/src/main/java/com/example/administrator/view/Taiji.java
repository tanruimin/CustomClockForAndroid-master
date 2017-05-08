package com.example.administrator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;

/**
 * data:2017/5/8
 * name:tanruimin tanruimin
 * function:
 */

public class Taiji extends View {

    private static final String TAG = "TJView";
    private int padding = 8;
    //画笔工具
    private Paint mPaint;
    //圆心坐标
    private float currentX = 0;
    private float currentY = 0;
    //大圆半径
    private float radiusBig = 200;
    //中圆半径
    private float radiusCenter = radiusBig / 2;
    //小圆半径
    private float radiusSmall = radiusCenter / 3;

    public Taiji(Context context) {
        this(context, null);
    }

    public Taiji(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Taiji(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    /**
     * 设置缩放动画
     */


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawLeftHalfCirle(canvas);
        drawTBCirle(canvas);
    }

    /**
     * 画上下两个圆--中圆和小圆
     *
     * @param canvas
     */
    private void drawTBCirle(Canvas canvas) {
        //画上面的白中圆
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(currentX, currentY - radiusBig / 2, radiusCenter, mPaint);
        //画上面的黑小圆
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(currentX, currentY - radiusBig / 2, radiusSmall, mPaint);
        //画下面的黑中圆
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(currentX, currentY + radiusBig / 2, radiusCenter, mPaint);
        //画下面的白小圆
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(currentX, currentY + radiusBig / 2, radiusSmall, mPaint);
    }

    /**
     * 画左边半圆
     *
     * @param canvas
     */
    private void drawLeftHalfCirle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(new RectF(currentX - radiusBig, currentY - radiusBig, currentX + radiusBig, currentY + radiusBig), 90, 180, true, mPaint);//90度开始画180度
    }

    /**
     * 画黑色的背景底板
     *
     * @param canvas
     */
    private void drawBg(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(currentX, currentY, radiusBig + padding, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        currentX = MeasureSpec.getSize(widthMeasureSpec) / 2;
        currentY = MeasureSpec.getSize(heightMeasureSpec) / 2;
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();
        invalidate();
        return true;
    }
}

package com.zkys.yun.ihome.view.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.zkys.yun.ihome.R;

public class RoundnessProgressBar extends HorizontalProgressBarWithNumber {

    private int mMaxPaintWidth;
    private int mRadius = dp2px(46);


    public RoundnessProgressBar(Context context) {
        this(context,null);
    }

    public RoundnessProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);


        mReachedProgressBarHeight = (int) (mUnReachedProgressBarHeight * 2.5f);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBarWidthNumber);
        mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBarWidthNumber_radius,mRadius);
        typedArray.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mMaxPaintWidth = Math.max(mReachedProgressBarHeight,mUnReachedProgressBarHeight);
        int expect  = mRadius * 2 + mMaxPaintWidth +getPaddingLeft()+ getPaddingRight();
        int width = resolveSize(expect,widthMeasureSpec);
        int height  = resolveSize(expect,heightMeasureSpec);
        int realWidth = Math.min(width,height);

        mRadius = (realWidth - getPaddingRight() - getPaddingLeft() - mMaxPaintWidth)/2;
        setMeasuredDimension(realWidth,realWidth);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress() + "分";
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent())/2;

        canvas.save();
        canvas.translate(getPaddingLeft() + mMaxPaintWidth/2,getPaddingTop() +mMaxPaintWidth/2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
        mPaint.setColor(mReachedBarColor);
        mPaint.setStrokeWidth(mReachedProgressBarHeight);
        float sweepAngle = getProgress() * 1.0f /getMax()* 360;
        canvas.drawArc(new RectF(0,0,mRadius * 2,mRadius *2),-90,sweepAngle,false,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,mRadius -textWidth /2,mRadius - textHeight,mPaint);
        canvas.restore();


    }
}

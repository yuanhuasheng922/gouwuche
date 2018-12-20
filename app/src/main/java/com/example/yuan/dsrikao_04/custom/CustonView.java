package com.example.yuan.dsrikao_04.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustonView  extends LinearLayout {
    //定义最高的海子
    private int mChildMaxHeight;
    //上下左右边距
    private int mHSpace=20;
    private int mVspace=20;
    public CustonView(Context context) {
        super(context);
    }

    public CustonView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public CustonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //找到最高的孩子
        findMaxChild();
        //定义左上
        int left=0,top=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View childAt = getChildAt(i);
            if (left!=0)
            {
                if ((left + childAt.getMeasuredWidth())>sizeWidth)
                {
                    top += mChildMaxHeight + mVspace;
                    left=0;
                }
            }
            left+=childAt.getMeasuredWidth() + mHSpace;
        }
        setMeasuredDimension(sizeWidth,(top + mChildMaxHeight)>sizeHeight ? sizeHeight : top+mChildMaxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChild();
        int top=0,left=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View childAt = getChildAt(i);
            if (left!=0)
            {
                if (left+childAt.getMeasuredWidth()>getWidth())
                {
                    top+=mChildMaxHeight +mVspace;
                    left=0;
                }
            }
            childAt.layout(left,top,left+childAt.getMeasuredWidth(),top+mChildMaxHeight);
            left+=childAt.getMeasuredWidth() +mHSpace;
        }
    }
    //画

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
//找到最高的孩子
    private void findMaxChild() {

        mChildMaxHeight=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View childAt = getChildAt(i);
            if (childAt.getMeasuredHeight()>mChildMaxHeight)
            {
                mChildMaxHeight=childAt.getMeasuredHeight();
            }
        }
    }
}

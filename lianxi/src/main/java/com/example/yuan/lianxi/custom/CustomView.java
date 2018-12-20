package com.example.yuan.lianxi.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustomView extends LinearLayout {

    private int MaxHeight;
    private int leftRight=20;
    private int topBottem=20;


    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        findMaxHeight();
        //系统检测的宽高
        final int sizaWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizaHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //宽度和高度
        int top=0,left=0;
        int count = getChildCount();
        for (int i=0;i<count;i++)
        {
            View childAt = getChildAt(i);
            if (left!=0)
            {
                //判断是否换行
                if (left + childAt.getMeasuredWidth() > sizaWidth)
                {
                    //计算歘下一行的高度
                    top+=MaxHeight +topBottem;
                    //换行
                    left=0;
                }
            }
            left +=childAt.getMeasuredWidth() +leftRight;
        }
        setMeasuredDimension(sizaWidth,top +MaxHeight >sizaHeight ?sizaHeight : top +MaxHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
       findMaxHeight();
       int left=0,top=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            View view = getChildAt(i);
            if (left!=0)
            {
                if (left +view.getMeasuredWidth()>getWidth())
                {
                    //计算出下一行的高度
                    top +=MaxHeight + topBottem;
                    left=0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+MaxHeight);
            left+=view.getMeasuredWidth()+leftRight;
        }
    }


    //得到最高的孩子
    private void findMaxHeight() {
        //最高度为0
        MaxHeight=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            //得到每个孩子的下标
            View childAt = getChildAt(i);
            if (childAt.getMeasuredHeight()>MaxHeight)
            {
                MaxHeight=childAt.getMeasuredHeight();
            }
        }
    }
}

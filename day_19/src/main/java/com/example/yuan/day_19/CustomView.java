package com.example.yuan.day_19;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yuan.day_19.adapter.Product_Adapter;

import java.util.List;

public class CustomView extends LinearLayout implements View.OnClickListener {

    private ImageView customViewadd;
    private ImageView customViewjian;
    private EditText custominput;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //布局
        View view=View.inflate(getContext(),R.layout.customview_item,null);
        //控件
        customViewadd = view.findViewById(R.id.customViewadd);
        customViewjian = view.findViewById(R.id.customViewjian);
        custominput = view.findViewById(R.id.custominput);
            customViewadd.setOnClickListener(this);
            customViewjian.setOnClickListener(this);
        addView(view);

        custominput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private int num;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {   //点击图片加加
            case R.id.customViewadd:
                num++;
                custominput.setText(num + "");
                list.get(position).setNum(num);
                callBackListener.callback();
                //局部刷新
                product_adapter.notifyItemChanged(position);
                break;
            //点击图片减减
            case R.id.customViewjian:
                    if (num>1)
                    {
                        num--;
                    }
                    else
                    {
                        Toast.makeText(getContext(),"我是有底线的啊",Toast.LENGTH_SHORT).show();
                    }

                    custominput.setText(num+"");
                    list.get(position).setNum(num);
                    callBackListener.callback();
                    product_adapter.notifyItemChanged(position);

                break;
                default:
                    break;
        }
    }

   private List<UserBean.DataBean.ListBean> list;
   private int position;
   private Product_Adapter product_adapter;
    public void setData(Product_Adapter product_adapter,int i,List<UserBean.DataBean.ListBean> list)
    {
        this.list=list;
        this.product_adapter=product_adapter;
        position=i;
        num =list.get(i).getNum();
        custominput.setText(num+"");

    }
CallBackListener callBackListener;

    public void setCallBackListener(CallBackListener listener) {
        this.callBackListener = listener;
    }



    public interface CallBackListener{
        void callback();
    }

}

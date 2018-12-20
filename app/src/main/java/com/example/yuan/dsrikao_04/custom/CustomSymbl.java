package com.example.yuan.dsrikao_04.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yuan.dsrikao_04.R;
import com.example.yuan.dsrikao_04.adapter.Prodect_Adapter;
import com.example.yuan.dsrikao_04.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class CustomSymbl extends LinearLayout implements View.OnClickListener {

    private ImageView custom_imageadd;
    private ImageView custom_imagejian;
    private EditText custom_input;

    public CustomSymbl(Context context) {
        super(context);
        init(context);
    }

    public CustomSymbl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomSymbl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.custon_item, null);
        custom_imageadd = view.findViewById(R.id.custom_imageadd);
        custom_imagejian = view.findViewById(R.id.custom_imagejian);
        custom_input = view.findViewById(R.id.custom_input);

        custom_imageadd.setOnClickListener(this);
        custom_imagejian.setOnClickListener(this);

        addView(view);

        custom_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //改变数量
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //点击事件
    public int num;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.custom_imageadd:
                    //设置数量 改变数量改变对象内容 回调 局部刷新
                num++;
                custom_input.setText(num+ "");
                datas.get(position).setNum(num);
               listener.callBack();
               prodect_adapter.notifyItemChanged(position);
                break;
            case R.id.custom_imagejian:
                if (num>1)
                {
                    num--;
                }
                else
                {
                    Toast.makeText(getContext(),"我是有底线的啊",Toast.LENGTH_SHORT).show();
                }
                custom_input.setText(num+"");
                datas.get(position).setNum(num);
                listener.callBack();
                prodect_adapter.notifyItemChanged(position);

                break;
        }
    }

    //传递的数据
    private List<ShopBean.DataBean.ListBean> datas =new ArrayList<>();
    private  int position;
    private Prodect_Adapter prodect_adapter;
    public void setData(Prodect_Adapter prodect_adapter, List<ShopBean.DataBean.ListBean> datas, int i)
    {
        this.datas = datas;
        this.prodect_adapter =prodect_adapter;
        position=i;
         num = datas.get(i).getNum();
         custom_input.setText(num + "");

    }


    //接口
    private CallBackListener listener;

    public void setOnCallback(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }

}

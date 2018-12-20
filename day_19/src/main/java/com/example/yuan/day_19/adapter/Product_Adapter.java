package com.example.yuan.day_19.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.day_19.CustomView;
import com.example.yuan.day_19.R;
import com.example.yuan.day_19.UserBean;

import java.util.ArrayList;
import java.util.List;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.MyViewHolder>{

    private List<UserBean.DataBean.ListBean> mDatas;
    private Context mContext;

    public Product_Adapter(List<UserBean.DataBean.ListBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    //布局
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.product_item,null);
        return new MyViewHolder(view);
    }

    @Override
    //绑定
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        String[] split = mDatas.get(i).getImages().split("\\|");
        Glide.with(mContext).load(split[0]).into(myViewHolder.product_image);

        myViewHolder.product_checkbox.setChecked(mDatas.get(i).isCheck());
        myViewHolder.product_price.setText(mDatas.get(i).getPrice()+ "");
        myViewHolder.product_title.setText(mDatas.get(i).getTitle());
        //myViewHolder.product_customview.setData(this, i, mDatas);
        //商家的跟商家的有所不同,商品添加了选中改变的监听

        //如果商家下面的勾选框够玩了就  商家的选框也选上
        myViewHolder.product_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //优先改变自己的状态
                mDatas.get(i).setCheck(isChecked);
                //回调,目的是告诉activity  有人选中状态改变
                if (product!=null)
                {
                    product.callback();
                }
            }
        });
        //设置自定义中加减edit
        myViewHolder.product_customview.setData(this,i,mDatas);
        myViewHolder.product_customview.setCallBackListener(new CustomView.CallBackListener() {
            @Override
            public void callback() {
                if (product!=null)
                {
                    product.callback();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    //拿到控件
    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox product_checkbox;
        private final ImageView product_image;
        private final TextView product_title;
        private final TextView product_price;
        private final CustomView product_customview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_checkbox = itemView.findViewById(R.id.product_checkbox);
            product_image = itemView.findViewById(R.id.product_image);
            product_title = itemView.findViewById(R.id.product_title);
            product_price = itemView.findViewById(R.id.product_price);
            product_customview = itemView.findViewById(R.id.product_customview);

        }
    }

    //点击商家商品全选
    public void selectOrRemoveAll(boolean isSelectAll)
    {
        for (UserBean.DataBean.ListBean listBean : mDatas)
        {
            listBean.setCheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

                    //定义接口
Product product;

    public void setProduct(Product mProduct) {
        this.product = mProduct;
    }

    public interface Product
    {
        void callback();
    }

}

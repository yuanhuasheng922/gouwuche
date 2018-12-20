package com.example.yuan.day20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.day20.R;
import com.example.yuan.day20.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class Right_Adapter extends RecyclerView.Adapter<Right_Adapter.MyViewHolder>{
        private List<ProductBean.DataBean.ListBean> mDatas;
        private Context mContext;

    public Right_Adapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setmmDatas(List<ProductBean.DataBean.ListBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.right_item,null);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(mContext).load(mDatas.get(i).getIcon()).into(myViewHolder.right_image);
        myViewHolder.right_name.setText(mDatas.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView right_image;
        private final TextView right_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            right_image = itemView.findViewById(R.id.right_image);
            right_name = itemView.findViewById(R.id.right_name);
        }
    }
}

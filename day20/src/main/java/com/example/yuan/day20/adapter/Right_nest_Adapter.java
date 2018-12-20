package com.example.yuan.day20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuan.day20.R;
import com.example.yuan.day20.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class Right_nest_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductBean.DataBean> mDatas;
    private Context mContext;

    public Right_nest_Adapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<ProductBean.DataBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.right_right_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            MyViewHolder holder= (MyViewHolder) viewHolder;
            holder.right_right_name.setText(mDatas.get(i).getName());
            //右侧有热词额外展示  右边的适配器把上一个右边展示的adapter 拿过来
        //适配器
            Right_Adapter right_adapter=new Right_Adapter(mContext);
            holder.right_right_recyclerView.setAdapter(right_adapter);
            //线性管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        holder.right_right_recyclerView.setLayoutManager(layoutManager);
        right_adapter.setmmDatas(mDatas.get(i).getList());



    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView right_right_name;
        private final RecyclerView right_right_recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            right_right_name = itemView.findViewById(R.id.right_right_name);
            right_right_recyclerView = itemView.findViewById(R.id.right_right_recyclerView);

        }
    }
}

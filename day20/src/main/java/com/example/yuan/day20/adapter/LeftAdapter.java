package com.example.yuan.day20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuan.day20.R;
import com.example.yuan.day20.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder>{

    private List<ShopBean.DataBean> mDatas;
    private Context mContext;

    public LeftAdapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<ShopBean.DataBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.left_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            //显示名字
        viewHolder.leftName.setText(mDatas.get(i).getName());
        //点击进去显示更多
        viewHolder.left_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener!=null)
                {
                    mOnClickListener.onClick(i,mDatas.get(i).getCid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout left_linearlayout;
        private final TextView leftName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            left_linearlayout = itemView.findViewById(R.id.left_linearlayout);
            leftName = itemView.findViewById(R.id.leftName);

        }
    }
    //接口
    OnClickListener mOnClickListener;

    public void setmOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public interface  OnClickListener{
        void onClick(int position,String cid);
    }

}

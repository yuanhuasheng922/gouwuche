package com.example.yuan.lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.lianxi.R;
import com.example.yuan.lianxi.bean.UserBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Linear_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<UserBean.DataBean> mDaatas;
    private Context mContext;
    private boolean isshow;

    public Linear_Adapter(Context mContext, boolean isshow) {
        this.mContext = mContext;
        this.isshow = isshow;
        mDaatas=new ArrayList<>();
    }

    //刷新


    public void setmDaatas(List<UserBean.DataBean> daatas) {
      mDaatas.clear();
      mDaatas.addAll(daatas);
      notifyDataSetChanged();

    }
    public void addDaatas(List<UserBean.DataBean> daatas) {

        mDaatas.addAll(daatas);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        XRecyclerView.ViewHolder viewHolder;
        if (isshow)
        {
            View view=LayoutInflater.from(mContext).inflate(R.layout.linear_iten,viewGroup,false);

            viewHolder=new ViewHolder(view);
        }
        else
        {
            View view=LayoutInflater.from(mContext).inflate(R.layout.grid_item,viewGroup,false);
            viewHolder=new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder= (ViewHolder) viewHolder;
        String[] split = mDaatas.get(i).getImages().split("\\|");
        Glide.with(mContext).load(split[0]).into(holder.linear_image);

        holder.linear_name.setText(mDaatas.get(i).getTitle());
        holder.linear_price.setText(mDaatas.get(i).getPrice()+ "");
    }

    @Override
    public int getItemCount() {
        return mDaatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView linear_image;
        private final TextView linear_name;
        private final TextView linear_price;
        private final Button linear_gouwuche;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linear_image = itemView.findViewById(R.id.linear_image);
            linear_name = itemView.findViewById(R.id.linear_name);
            linear_price = itemView.findViewById(R.id.linear_price);
            linear_gouwuche = itemView.findViewById(R.id.linear_gouwuche);

        }
    }


}

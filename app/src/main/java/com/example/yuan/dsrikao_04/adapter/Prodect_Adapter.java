package com.example.yuan.dsrikao_04.adapter;

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
import com.example.yuan.dsrikao_04.R;
import com.example.yuan.dsrikao_04.bean.ShopBean;
import com.example.yuan.dsrikao_04.custom.CustomSymbl;

import java.util.List;

public class Prodect_Adapter extends RecyclerView.Adapter<Prodect_Adapter.MyViewHolder>{
    private List<ShopBean.DataBean.ListBean> mDatas;
    private Context mContext;

    public Prodect_Adapter(List<ShopBean.DataBean.ListBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.produce_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        //设置图片
        String[] split = mDatas.get(i).getImages().split("\\|");
        Glide.with(mContext).load(split[0]).into(myViewHolder.product_image);
        //设置名字,价格
        myViewHolder.product_price.setText(mDatas.get(i).getPrice()+ "");
        myViewHolder.product_title.setText(mDatas.get(i).getTitle());
        myViewHolder.product_check.setChecked(mDatas.get(i).isCheck());

        //接口
        myViewHolder.product_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //优先改变自己的状态
                mDatas.get(i).setCheck(isChecked);
                //回调.目的是告诉activity,有人选中状态被改变
                if (mShopCallBackListener!=null)
                {
                    mShopCallBackListener.callBack();
                }
            }
        });

        myViewHolder.product_custon.setData(this,mDatas,i);
        myViewHolder.product_custon.setOnCallback(new CustomSymbl.CallBackListener() {
            @Override
            public void callBack() {
                if (mShopCallBackListener!=null)
                {
                    mShopCallBackListener.callBack();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {

        private final CheckBox product_check;
        private final ImageView product_image;
        private final TextView product_title;
        private final TextView product_price;
        private final CustomSymbl product_custon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_check = itemView.findViewById(R.id.product_check);
            product_image = itemView.findViewById(R.id.product_image);
            product_title = itemView.findViewById(R.id.product_title);
            product_price = itemView.findViewById(R.id.product_price);
            product_custon = itemView.findViewById(R.id.product_custon);


        }
    }
    //在我们子商品的adapter中,修改子商品的全选和反选
    public void selectPrRemoveAll(boolean isSelectAll)
    {
        for (ShopBean.DataBean.ListBean listBean : mDatas)
        {
            listBean.setCheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

    //接口
    private ShopCallBackListener mShopCallBackListener;

    public void  setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    //接口
    public interface ShopCallBackListener{
        void callBack();
    }
}

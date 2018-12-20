package com.example.yuan.dsrikao_04.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuan.dsrikao_04.R;
import com.example.yuan.dsrikao_04.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class Shop_Adapter extends RecyclerView.Adapter<Shop_Adapter.ViewHolder>{
    private List<ShopBean.DataBean> mDatas =new ArrayList<>();
    private Context mContext;

    public Shop_Adapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }
    public void setmDatas(List<ShopBean.DataBean> datas) {
       this.mDatas=datas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.shop_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //设置名字
           viewHolder.shop_title.setText(mDatas.get(i).getSellerName());
           //设置选中框
           viewHolder.shop_check.setChecked(mDatas.get(i).isCheck());
           //商品适配器
        final Prodect_Adapter prodect_adapter=new Prodect_Adapter(mDatas.get(i).getList(),mContext);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        viewHolder.shop_recyclerView.setLayoutManager(layoutManager);
        viewHolder.shop_recyclerView.setAdapter(prodect_adapter);

        //接口
            prodect_adapter.setListener(new Prodect_Adapter.ShopCallBackListener() {
                @Override
                public void callBack() {
                    //从商品适配里回调过来 回给antivity,activity计算价格和数量
                    if (mShopCallBackListener!=null)
                    {
                        mShopCallBackListener.callBack(mDatas);
                    }

                    List<ShopBean.DataBean.ListBean> listBeans = mDatas.get(i).getList();
                    boolean isAllChecked=true;
                    for (ShopBean.DataBean.ListBean bean : listBeans)
                    {
                        if (!bean.isCheck())
                        {
                            //只要有一个商品未选中 标志位设置为false 并且跳出循环
                            isAllChecked=false;
                            break;
                        }
                    }
                        //刷新商家的状态
                    viewHolder.shop_check.setChecked(isAllChecked);
                    mDatas.get(i).setCheck(isAllChecked);
                }
            });

            //监听checkbox 的点击事件
        //目的是改变旗下所有商品的选中状态
        viewHolder.shop_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先改变自己的标志位
                mDatas.get(i).setCheck(viewHolder.shop_check.isChecked());
                //调用产品adapter 的方法 用来全选和反选
                prodect_adapter.selectPrRemoveAll(viewHolder.shop_check.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    //获取控件
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView shop_title;
        private final CheckBox shop_check;
        private final RecyclerView shop_recyclerView;

        public ViewHolder(@NonNull View itemView) {
        super(itemView);
            shop_title = itemView.findViewById(R.id.shop_title);
            shop_check = itemView.findViewById(R.id.shop_check);
            shop_recyclerView = itemView.findViewById(R.id.shop_recyclerView);
    }
}
        //接口
      private   ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener{
        void callBack(List<ShopBean.DataBean> datas);
    }
}

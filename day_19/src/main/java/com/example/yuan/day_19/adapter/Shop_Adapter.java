package com.example.yuan.day_19.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuan.day_19.R;
import com.example.yuan.day_19.UserBean;

import java.util.ArrayList;
import java.util.List;

public class Shop_Adapter extends RecyclerView.Adapter<Shop_Adapter.ViewHolder>{
        private List<UserBean.DataBean> mDatas;
        private Context mContext;

    public Shop_Adapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<UserBean.DataBean> datas) {
       // this.mDatas = datas;
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //视图
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.shop_adapter,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //绑定数据
            viewHolder.shop_checkbox.setChecked(mDatas.get(i).isCheck());
            viewHolder.shop_title.setText(mDatas.get(i).getSellerName());
        //shop_recyclerView
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
       // layoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.shop_recyclerView.setLayoutManager(layoutManager);
        //适配器
       final Product_Adapter adapter=new Product_Adapter(mDatas.get(i).getList(),mContext);
       viewHolder.shop_recyclerView.setAdapter(adapter);


       adapter.setProduct(new Product_Adapter.Product() {
           @Override
           public void callback() {
               //从商品适配器回调回来  activity 计算价格数量
               if(product!=null)
               {
                  product.callback(mDatas);
               }
               List<UserBean.DataBean.ListBean> listBeans = mDatas.get(i).getList();
               boolean isAllChecked =true;
               for (UserBean.DataBean.ListBean bean : listBeans)
               {
                   if (!bean.isCheck())
                   {
                       isAllChecked=false;
                       break;
                   }
               }
               viewHolder.shop_checkbox.setChecked(isAllChecked);
           }
       });
            //点击商家全选商品
       viewHolder.shop_checkbox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mDatas.get(i).setCheck(viewHolder.shop_checkbox.isChecked());
               adapter.selectOrRemoveAll(viewHolder.shop_checkbox.isChecked());
           }
       });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    //拿到控件
    static  class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox shop_checkbox;
        private final TextView shop_title;
        private final RecyclerView shop_recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shop_checkbox = itemView.findViewById(R.id.shop_checkbox);
            shop_title = itemView.findViewById(R.id.shop_title);
            shop_recyclerView = itemView.findViewById(R.id.shop_recyclerView);
        }
    }
                        //接口
    Product product;

    public void setProduct(Product mProduct) {
        this.product = mProduct;
    }

    public interface Product
    {
        void callback(List<UserBean.DataBean> mDatas);
    }
}

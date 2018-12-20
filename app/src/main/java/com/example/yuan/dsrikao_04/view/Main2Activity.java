package com.example.yuan.dsrikao_04.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuan.dsrikao_04.R;
import com.example.yuan.dsrikao_04.adapter.Shop_Adapter;
import com.example.yuan.dsrikao_04.api.Apis;
import com.example.yuan.dsrikao_04.bean.ShopBean;
import com.example.yuan.dsrikao_04.presenter.IPresenterImple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView,View.OnClickListener {

    private CheckBox checkbox_all;
    private TextView all_price;
    private TextView close_price;
    private IPresenterImple presenterImple;
    private RecyclerView recyclerView;
    private Shop_Adapter shop_adapter;
    private List<ShopBean.DataBean> mDatas =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        checkbox_all = findViewById(R.id.checkbox_all);
        all_price = findViewById(R.id.all_price);
        close_price = findViewById(R.id.close_price);
        recyclerView = findViewById(R.id.recyclerView);
        checkbox_all.setOnClickListener(this);
        //p实例
        presenterImple = new IPresenterImple(this);
        //适配器
        shop_adapter = new Shop_Adapter(this);
        recyclerView.setAdapter(shop_adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
            //接口
        shop_adapter.setListener(new Shop_Adapter.ShopCallBackListener() {
            @Override
            public void callBack(List<ShopBean.DataBean> datas) {
                //在这里重新便利 已经改状态后的数据
                //在这里不能break 跳出 因为 还需要计算后面点击商品的价格和数目,所以必须跑完整个循环
                double totalPrice=0;
                //勾选商品的数量  不是该商品购买的数量
                int num=0;
                //所有商品总数,和上面的数量作比对,如果两者相等,则说明全选
                int totalNum=0;
                for (int a=0;a<datas.size();a++)
                {
                    //获取商家里的商品
                    List<ShopBean.DataBean.ListBean> datasAll = datas.get(a).getList();
                    for (int i=0;i<datasAll.size();i++)
                    {
                      totalNum=  totalNum+datasAll.get(i).getNum();
                      //取选中的状态
                        if (datasAll.get(i).isCheck())
                        {
                           totalPrice = totalPrice + (datasAll.get(i).getPrice() * datasAll.get(i).getNum());
                           num=num +datasAll.get(i).getNum();
                        }
                    }
                }
                if (num<totalNum)
                {
                    //不是全选中
                   checkbox_all.setChecked(false);
                }
                else
                {
                    checkbox_all.setChecked(true);
                }
                all_price.setText("合计:"+totalPrice+"");
                close_price.setText("去结算:"+num);

            }
        });

        getDatas();
    }

    private void getDatas() {
        Map<String,String>  params=new HashMap<>();
        params.put("uid","71");
        presenterImple.getRequest(Apis.TYPE_TEXT,ShopBean.class,params);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.checkbox_all:
                        checkSeller(checkbox_all.isChecked());
                        shop_adapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
    }

    private void checkSeller(boolean bool) {

        double totalPrice=0;
        int num=0;
        for (int a=0;a<mDatas.size();a++)
        {
            //便利商家 .改变状态
            ShopBean.DataBean dataBean = mDatas.get(a);
            dataBean.setCheck(bool);
            List<ShopBean.DataBean.ListBean> listAll = mDatas.get(a).getList();
            for (int i=0;i<listAll.size();i++)
            {
                //便利商品 改变状态
                listAll.get(i).setCheck(bool);
                totalPrice=totalPrice +(listAll.get(i).getPrice() * listAll.get(i).getNum());
                num=num+listAll.get(i).getNum();
            }
        }
        if (bool)
        {
            all_price.setText("合计:"+totalPrice+"");
            close_price.setText("去结算:"+num);
        }
        else
        {
            all_price.setText("合计:+0.00+");
            close_price.setText("去结算:(0)");
        }
    }

    @Override
    public void getRequest(Object data) {
        if (data instanceof ShopBean)
        {
            ShopBean shopBean= (ShopBean) data;
           mDatas=shopBean.getData();
           if (mDatas!=null)
           {
               mDatas.remove(0);
               shop_adapter.setmDatas(mDatas);
           }
        }
    }


}

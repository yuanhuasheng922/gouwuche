package com.example.yuan.day_19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuan.day_19.adapter.Shop_Adapter;
import com.example.yuan.day_19.api.Apis;
import com.example.yuan.day_19.presenter.IPresenterIMprl;
import com.example.yuan.day_19.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private CheckBox activity_checkbox;
    private TextView activity_price;
    private RecyclerView activity_recyclerView;
    private Button activity_num;
    private IPresenterIMprl presenterIMprl;
    private Shop_Adapter shop_adapter;
    private List<UserBean.DataBean> mDatas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        //控件
        activity_checkbox = findViewById(R.id.activity_checkbox);
        activity_price = findViewById(R.id.activity_price);
        activity_recyclerView = findViewById(R.id.activity_recyclerView);
        activity_num = findViewById(R.id.activity_num);
        activity_checkbox.setOnClickListener(this);
        //p实例
        presenterIMprl = new IPresenterIMprl(this);
            //线性
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        activity_recyclerView.setLayoutManager(layoutManager);
        //适配器
        shop_adapter = new Shop_Adapter(this);
        activity_recyclerView.setAdapter(shop_adapter);
        //商家和商品选中的时候的价格以及数量改变
        shop_adapter.setProduct(new Shop_Adapter.Product() {
            @Override
            public void callback(List<UserBean.DataBean> mDatas) {
                //在这里重新便利已经改状态后的数据
                double totalPrice = 0;
                //勾选商品的数量,不是该商品购买的数量
                int num=0;
                //所有商品总数,和上面的数量做比对,如果两者相等,则说明全选
                int totalNum = 0;
                for (int a=0;a<mDatas.size();a++)
                {
                    List<UserBean.DataBean.ListBean> listAll = mDatas.get(a).getList();
                    for(int i=0;i<listAll.size();i++)
                    {
                    totalNum =    totalNum + listAll.get(i).getNum();
                    //取选中的状态
                        if (listAll.get(i).isCheck()) {

                            totalPrice += listAll.get(i).getPrice() * listAll.get(i).getNum();
                            num = num +listAll.get(i).getNum();
                        }
                    }
                    if (num<totalNum)
                    {
                        //不是全选中
                        activity_checkbox.setChecked(false);
                    }
                    else
                    {
                        activity_checkbox.setChecked(true);
                    }
                    activity_price.setText("合计" +totalPrice +"");
                    activity_num.setText("去结算"+num);

                }
            }
        });

        getData();
    }
    //数据
    private void getData() {
        Map<String,String> params =new HashMap<>();
        params.put("uid","71");
        presenterIMprl.getRequest(Apis.TYPE_TEXT,UserBean.class,params);
    }

    //数据
    @Override
    public void getRequest(Object data) {
        if (data instanceof UserBean)
        {
            UserBean userBean= (UserBean) data;
           mDatas = userBean.getData();
           if (mDatas!=null)
           {
               mDatas.remove(0);
               shop_adapter.setmDatas(mDatas);
           }
        }
    }


    //点击全选反选事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_checkbox:
                checkSeller(activity_checkbox.isChecked());
                shop_adapter.notifyDataSetChanged();
                break;
            default:
        }

}
    //全选的价格数量
    private void checkSeller(boolean bool) {

        double totalPrice =0;
        int num =0;
        for (int a=0;a<mDatas.size();a++)
        {
            //便利商家
            UserBean.DataBean dataBean = mDatas.get(a);
            dataBean.setCheck(bool);
            List<UserBean.DataBean.ListBean> listAll = mDatas.get(a).getList();
            for (int i=0;i<listAll.size();i++)
            {
                //便利商品  改变状态
                listAll.get(i).setCheck(bool);
                totalPrice=totalPrice +(listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num +listAll.get(i).getNum();
            }
        }
        if (bool)
        {
            activity_price.setText("合计" +totalPrice +"");
            activity_num.setText("去结算"+num);
        }
        else
        {
            activity_price.setText("合计" +0);
            activity_num.setText("去结算"+0);
        }

    }
    }

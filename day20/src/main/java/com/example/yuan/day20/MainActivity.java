package com.example.yuan.day20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yuan.day20.adapter.LeftAdapter;
import com.example.yuan.day20.adapter.Right_Adapter;
import com.example.yuan.day20.adapter.Right_nest_Adapter;
import com.example.yuan.day20.api.Apis;
import com.example.yuan.day20.bean.ProductBean;
import com.example.yuan.day20.bean.ShopBean;
import com.example.yuan.day20.presenter.IPresenterImple;
import com.example.yuan.day20.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private RecyclerView recyclerView_left;
    private IPresenterImple presenterImple;
    private LeftAdapter leftAdapter;
    private RecyclerView recyclerView_right;
    private Right_Adapter right_adapter;
    private Right_nest_Adapter right_nest_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenterImple = new IPresenterImple(MainActivity.this);
        initView();
        leftGetData();

    }



    private void initView() {
        //左边
        recyclerView_left = findViewById(R.id.recyclerView_left);
        //线性管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_left.setLayoutManager(layoutManager);

        leftAdapter = new LeftAdapter(this);
        recyclerView_left.setAdapter(leftAdapter);
        //接口
        leftAdapter.setmOnClickListener(new LeftAdapter.OnClickListener() {
            @Override
            public void onClick(int position, String cid) {
                rightGetData(cid);
            }
        });


        //右边
        recyclerView_right = findViewById(R.id.recyclerView_right);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_right.setLayoutManager(layoutManager1);
        //用嵌套的adapter
        right_nest_adapter = new Right_nest_Adapter(this);
        recyclerView_right.setAdapter(right_nest_adapter);


    }

    private void leftGetData() {
        Map<String,String> params=new HashMap<>();
        presenterImple.getReequest(Apis.TYPE_LEFT,ShopBean.class,params);


    }

    private void rightGetData(String cid) {
        Map<String,String> params=new HashMap<>();
        params.put("cid",cid);
        presenterImple.getReequest(Apis.TYPE_LEFT,ProductBean.class,params);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getRequest(Object data) {
        if (data instanceof  ShopBean)
        {
           ShopBean shopBean = (ShopBean) data;
           leftAdapter.setmDatas(shopBean.getData());
        }

        if (data instanceof  ProductBean)
        {
            ProductBean productBean = (ProductBean) data;
          right_nest_adapter.setmDatas(productBean.getData());
        }

    }
}

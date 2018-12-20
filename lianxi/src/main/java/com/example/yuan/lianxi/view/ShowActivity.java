package com.example.yuan.lianxi.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuan.lianxi.R;
import com.example.yuan.lianxi.adapter.Linear_Adapter;
import com.example.yuan.lianxi.apis.Api;
import com.example.yuan.lianxi.bean.UserBean;
import com.example.yuan.lianxi.presenter.IPresenterImple;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class ShowActivity extends AppCompatActivity implements IView,OnClickListener {

    private IPresenterImple presenterImple;
    private Button show_sousuo;
    private Button show_qiehuan;
    private EditText show_input;
    private TextView show_zonghe;
    private TextView show_price;
    private TextView show_num;

    private  boolean isShow;
    private int mPage=1;
    private XRecyclerView show_xrecyclerview;
    private Linear_Adapter linear_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initView();
        presenterImple = new IPresenterImple(this);
    }

    private void initView() {

        show_sousuo = findViewById(R.id.show_sousuo);
        show_qiehuan = findViewById(R.id.show_qiehuan);
        show_input = findViewById(R.id.show_input);
        show_zonghe = findViewById(R.id.show_zonghe);
        show_price = findViewById(R.id.show_price);
        show_num = findViewById(R.id.show_num);
        show_xrecyclerview = findViewById(R.id.show_xrecyclerview);

        show_qiehuan.setOnClickListener(this);

        show_xrecyclerview.setLoadingMoreEnabled(true);
        show_xrecyclerview.setPullRefreshEnabled(true);

        show_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                getDatas();
            }

            @Override
            public void onLoadMore() {
                getDatas();
            }
        });

        if (isShow)
        {
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            show_xrecyclerview.setLayoutManager(layoutManager);
        }
        else
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
           gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            show_xrecyclerview.setLayoutManager(gridLayoutManager);
        }
        isShow=!isShow;
        mPage=1;
        linear_adapter = new Linear_Adapter(this,isShow);
        show_xrecyclerview.setAdapter(linear_adapter);


    }

    public void getDatas()
    {
        Map<String,String> params=new HashMap<>();
        params.put("keywords","手机");
        params.put("page",mPage+ "");
        presenterImple.getRequest(Api.TYPE_TEXT,UserBean.class,params);
    }
    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.show_qiehuan:
            initView();
            getDatas();
            break;
    }
    }

    @Override
    public void getRequest(Object data) {

        if (data instanceof UserBean)
        {
            UserBean userBean= (UserBean) data;
           if (mPage==1)
           {
               linear_adapter.setmDaatas(userBean.getData());
           }
           else
           {
               linear_adapter.addDaatas(userBean.getData());
           }
           mPage++;
           show_xrecyclerview.refreshComplete();
           show_xrecyclerview.loadMoreComplete();
        }
    }
}

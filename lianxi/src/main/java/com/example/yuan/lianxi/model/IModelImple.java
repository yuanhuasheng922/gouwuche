package com.example.yuan.lianxi.model;

import com.example.yuan.lianxi.callback.ICallBack;
import com.example.yuan.lianxi.callback.MyCallBack;
import com.example.yuan.lianxi.utils.OkHttp;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallBack callBack) {
        OkHttp.getInstance().poatEnqueue(url, clazz, params, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                callBack.getRequest(obj);
            }

            @Override
            public void onFail(Exception e) {
                callBack.getRequest(e);
            }
        });
    }
}

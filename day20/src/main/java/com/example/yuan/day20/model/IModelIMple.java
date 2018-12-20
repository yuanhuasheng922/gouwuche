package com.example.yuan.day20.model;

import com.example.yuan.day20.callback.ICallBack;
import com.example.yuan.day20.callback.MyCallback;
import com.example.yuan.day20.utils.OkHttps;

import java.util.Map;

public class IModelIMple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallback callback) {
        OkHttps.getInstance().postEnqueue(url, clazz, params, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callback.getRequest(obj);
            }

            @Override
            public void onfail(Exception e) {
                callback.getRequest(e.getMessage());
            }
        });
    }
}

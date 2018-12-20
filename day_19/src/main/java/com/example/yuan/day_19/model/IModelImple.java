package com.example.yuan.day_19.model;

import com.example.yuan.day_19.callback.ICallBack;
import com.example.yuan.day_19.callback.MyCallback;
import com.example.yuan.day_19.okhttps.OkHttp;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallback callback) {
        OkHttp.getInstance().postEnqueue(url, clazz, params, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                callback.getRequest(obj);
            }

            @Override
            public void onFail(Exception e) {
                callback.getRequest(e);
            }
        });
    }
}

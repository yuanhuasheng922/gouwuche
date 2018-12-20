package com.example.yuan.lianxi.model;

import com.example.yuan.lianxi.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class clazz, Map<String,String> params, MyCallBack callBack);
}

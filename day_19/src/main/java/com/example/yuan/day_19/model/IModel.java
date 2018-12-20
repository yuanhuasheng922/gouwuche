package com.example.yuan.day_19.model;

import com.example.yuan.day_19.callback.MyCallback;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class clazz, Map<String,String> params, MyCallback callback);
}

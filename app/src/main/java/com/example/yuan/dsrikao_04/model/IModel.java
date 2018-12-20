package com.example.yuan.dsrikao_04.model;

import com.example.yuan.dsrikao_04.callback.MyCallback;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class clazz, Map<String,String> params, MyCallback callback);
}

package com.example.yuan.day_19.presenter;

import java.util.Map;

public interface IPresenter {
    void getRequest(String url, Class clazz, Map<String,String> params);
}

package com.example.yuan.day20.presenter;

import java.util.Map;

public interface IPresenter {
    void getReequest(String url, Class clazz, Map<String,String> params);
}

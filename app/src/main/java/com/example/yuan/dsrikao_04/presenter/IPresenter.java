package com.example.yuan.dsrikao_04.presenter;

import java.util.Map;

public interface IPresenter  {
    void getRequest(String url, Class clazz, Map<String,String> params);
}

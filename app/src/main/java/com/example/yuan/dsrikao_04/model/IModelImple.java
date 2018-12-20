package com.example.yuan.dsrikao_04.model;

import com.example.yuan.dsrikao_04.callback.ICallback;
import com.example.yuan.dsrikao_04.callback.MyCallback;
import com.example.yuan.dsrikao_04.http.OkHttp;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallback callback) {
        OkHttp.getInstance().postEnqueue(url, clazz, params, new ICallback() {
            @Override
            public void onSucess(Object obj) {
                callback.getRequest(obj);
            }

            @Override
            public void onFAOLD(Exception e) {
                callback.getRequest(e);
            }
        });
    }
}

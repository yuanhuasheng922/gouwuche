package com.example.yuan.lianxi.presenter;

import com.example.yuan.lianxi.callback.MyCallBack;
import com.example.yuan.lianxi.model.IModelImple;
import com.example.yuan.lianxi.view.IView;

import java.util.Map;

public class IPresenterImple implements IPresenter {
    private IView mIView;
    private IModelImple mIModelImple;

    public IPresenterImple(IView mIView) {
        this.mIView = mIView;
        mIModelImple=new IModelImple();
    }

    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params) {
        mIModelImple.getRequest(url, clazz, params, new MyCallBack() {
            @Override
            public void getRequest(Object data) {
                mIView.getRequest(data);
            }
        });
    }
}

package com.example.yuan.day20.presenter;

import com.example.yuan.day20.callback.MyCallback;
import com.example.yuan.day20.model.IModelIMple;
import com.example.yuan.day20.view.IView;

import java.util.Map;

public class IPresenterImple implements IPresenter {
    private IView mIView;
    private IModelIMple mIModelImple;

    public IPresenterImple(IView mIView) {
        this.mIView = mIView;
        mIModelImple=new IModelIMple();
    }

    @Override
    public void getReequest(String url, Class clazz, Map<String, String> params) {
        mIModelImple.getRequest(url, clazz, params, new MyCallback() {
            @Override
            public void getRequest(Object data) {
                mIView.getRequest(data);
            }
        });
    }
}

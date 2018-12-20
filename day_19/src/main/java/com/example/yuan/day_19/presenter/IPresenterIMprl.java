package com.example.yuan.day_19.presenter;

import com.example.yuan.day_19.callback.MyCallback;
import com.example.yuan.day_19.model.IModelImple;
import com.example.yuan.day_19.view.IView;

import java.util.Map;

public class IPresenterIMprl implements IPresenter {
    private IView mIView;
    private IModelImple mImodelImple;

    public IPresenterIMprl(IView mIView) {
        this.mIView = mIView;
        mImodelImple=new IModelImple();
    }

    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params) {
     mImodelImple.getRequest(url, clazz, params, new MyCallback() {
         @Override
         public void getRequest(Object data) {
             mIView.getRequest(data);
         }
     });
    }
}

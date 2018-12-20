package com.example.yuan.dsrikao_04.presenter;

import com.example.yuan.dsrikao_04.callback.MyCallback;
import com.example.yuan.dsrikao_04.model.IModelImple;
import com.example.yuan.dsrikao_04.view.IView;

import java.util.Map;

public class IPresenterImple implements IPresenter {
    private IView mIView;
    private IModelImple mImodelImple;

    public IPresenterImple(IView mIView) {
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

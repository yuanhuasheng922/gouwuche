package com.example.yuan.lianxi.callback;

import android.widget.EditText;

public interface ICallBack<T>{
    void onSuccess(Object obj);
    void onFail(Exception e);
}

package com.example.yuan.day_19.callback;

public interface ICallBack<T> {
    void onSuccess(Object obj);
    void onFail(Exception e);
}

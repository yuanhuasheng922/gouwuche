package com.example.yuan.day20.callback;

public interface ICallBack<T> {
    void onsuccess(Object obj);
    void onfail(Exception e);
}

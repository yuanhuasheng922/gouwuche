package com.example.yuan.dsrikao_04.callback;

public interface ICallback<T> {
    void onSucess(Object obj);
    void onFAOLD(Exception e);
}

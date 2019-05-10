package com.example.myapplication.model;

public interface IModel {
    void requestData(String usetName,String pwd,CallBack callBack);
    interface CallBack{
        void setData(String s);
    }
}

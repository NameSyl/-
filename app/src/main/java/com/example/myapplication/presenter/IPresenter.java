package com.example.myapplication.presenter;

import com.example.myapplication.view.IView;

public interface IPresenter {
    void onAttch(IView iView);
    void startRequest(String userName,String pwd);
    void onDeAttch();
}

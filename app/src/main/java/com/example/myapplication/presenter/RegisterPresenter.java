package com.example.myapplication.presenter;

import com.example.myapplication.model.IModel;
import com.example.myapplication.model.RegisterModel;
import com.example.myapplication.view.IView;
//p层的实现类
/*
* P层的主要作用是 平衡Model 个 View 的一个桥梁
* 本身Model 和 View 之间是没有交互的  两者之间的交互只能通过P层
* 所以在P层 要同时获取Model 和 View
* 以便于操作
* */
public class RegisterPresenter implements IPresenter {
    private IModel iModel;
    private IView iView;
    public RegisterPresenter(IView iView){
        iModel = new RegisterModel();
        this.iView = iView;
    }
    @Override
    public void onAttch(IView iView) {
        this.iView = iView;
    }

    @Override
    public void startRequest(String userName, String pwd) {
        iModel.requestData(userName, pwd, new IModel.CallBack() {
            @Override
            public void setData(String s) {
                iView.getResponse(s);
            }
        });
    }

    @Override
    public void onDeAttch() {
        if (iModel!=null){
            iModel = null;
        }
        if (iView!=null){
            iView = null;
        }
    }
}

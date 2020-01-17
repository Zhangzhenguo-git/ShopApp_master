package com.dc.shopapp.presenter;

import com.dc.shopapp.model.MainModel;
import com.dc.shopapp.view.MainViewCallback;

import java.util.Map;

/**
 * @author Zhangzhenguo
 * @create 2020/1/16
 * @Email 18311371235@163.com
 * @Describe
 */
public class MainPresenter implements MainPresenterCallback{
    private MainViewCallback mainViewCallback;
    private MainModel mainModel;

    public MainPresenter(MainViewCallback mainViewCallback){
        this.mainViewCallback = mainViewCallback;
        mainModel=new MainModel();
    }

    @Override
    public void reqShopAppData(Map<String, Object> mapData) {
        mainModel.reqShopAppData(mapData, new MainModel.onReqResult() {
            @Override
            public void onSuccess(String result) {
                mainViewCallback.showMsg(result);
            }

            @Override
            public void onError(String msg) {
                mainViewCallback.errorMsg(msg);
            }
        });
    }
}

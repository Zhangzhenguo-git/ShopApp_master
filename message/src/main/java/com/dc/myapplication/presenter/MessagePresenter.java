package com.dc.myapplication.presenter;

import com.dc.myapplication.contract.MessageContract;
import com.dc.myapplication.model.MessageModel;

/**
 * @author Zhangzhenguo
 * @create 2020/1/20
 * @Email 18311371235@163.com
 * @Describe
 */
public class MessagePresenter implements MessageContract.Presenter{

    private MessageContract.View mView;
    private MessageModel messageModel;
    public MessagePresenter(MessageContract.View mView){
        this.mView=mView;
        messageModel=new MessageModel();
    }

    @Override
    public void getMessage() {
        messageModel.getMessage(new MessageModel.MessageCallback() {

            @Override
            public void onSuccess(String json) {
                mView.onSuccess(json);
            }

            @Override
            public void onError(String msg) {
                mView.onError(msg);
            }
        });
    }
}

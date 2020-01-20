package com.dc.shopapp.presenter;

import com.dc.shopapp.model.MessageModel;
import com.dc.shopapp.view.MessageCallback;

/**
 * @author Zhangzhenguo
 * @create 2020/1/20
 * @Email 18311371235@163.com
 * @Describe
 */
public class MessagePresenter implements MessagePresenterCallback{

    private MessageCallback callback;
    private MessageModel messageModel;
    public MessagePresenter(MessageCallback callback){
        this.callback=callback;
        messageModel=new MessageModel();
    }

    @Override
    public void getMessage() {
        messageModel.getMessage(new MessageModel.MessageCallback() {

            @Override
            public void onSuccess(String json) {
                callback.onSuccess(json);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}

package com.dc.myapplication.contract;

import com.dc.myapplication.model.MessageModel;

/**
 * @author Zhangzhenguo
 * @create 2020/1/21
 * @Email 18311371235@163.com
 * @Describe
 */
public interface MessageContract {

    public interface Model {
        void getMessage(MessageModel.MessageCallback callback);
    }

    public interface View {
        void onSuccess(String result);
        void onError(String msg);
    }

    public interface Presenter {
        void getMessage();
    }


}

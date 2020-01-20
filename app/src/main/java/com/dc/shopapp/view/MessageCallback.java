package com.dc.shopapp.view;

/**
 * @author Zhangzhenguo
 * @create 2020/1/20
 * @Email 18311371235@163.com
 * @Describe
 */
public interface MessageCallback {
    void onSuccess(String result);
    void onError(String msg);
}

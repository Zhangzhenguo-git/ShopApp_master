package com.dc.shopapp.model;

import android.app.Activity;
import android.content.Context;

import java.util.List;

/**
 * @author Zhangzhenguo
 * @create 2020/1/17
 * @Email 18311371235@163.com
 * @Describe
 */
public class InstallDownCallback {
    private static final String TAG="InstallDownCallback";
    private Activity mActivity;
    private InstallDownCallback mCallback;
    private List<setOnInstallDownListener> mListeners;

    public InstallDownCallback(Activity activity){
        this.mActivity=activity;
    }

    /**
     * 下载入口，根据需求添加各种参数，通过此方法调用网络请求下载接口
     */
    public void down(){
//        下载中，下载文件，下载成功或失败通知在下方接口

    }


    public interface setOnInstallDownListener{
        void onProgress();
        void onDrawFile();
        void onSuccess();
        void onError();
    }
}

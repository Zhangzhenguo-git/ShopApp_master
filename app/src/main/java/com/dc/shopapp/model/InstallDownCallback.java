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


    public interface setOnInstallDownListener{
        void onProgress();
        void onDrawFile();
        void onSuccess();
        void onError();
    }
}

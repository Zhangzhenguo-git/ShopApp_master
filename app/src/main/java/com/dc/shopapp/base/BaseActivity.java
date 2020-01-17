package com.dc.shopapp.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author Zhangzhenguo
 * @create 2020/1/16
 * @Email 18311371235@163.com
 * @Describe
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }
    public abstract int setContentView();
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();
}

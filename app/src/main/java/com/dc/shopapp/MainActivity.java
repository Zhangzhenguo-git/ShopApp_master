package com.dc.shopapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.dc.shopapp.adapter.ShopAdapter;
import com.dc.shopapp.base.BaseActivity;
import com.dc.shopapp.bean.MainResultData;
import com.dc.shopapp.presenter.MainPresenter;
import com.dc.shopapp.view.MainViewCallback;
import com.dc.shopapp.weight.HeadView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainViewCallback {
    @BindView(R.id.shopHead)
    HeadView shopHead;
    @BindView(R.id.shopRecyView)
    RecyclerView shopRecyView;

    private Context mContext;
    private MainPresenter mainPresenter;
    private ShopAdapter mShopAdapter;
    private List<MainResultData> showData;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mContext=MainActivity.this;
        mainPresenter=new MainPresenter(this);
        shopHead.setHeadShowOrHide(View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
        shopHead.setHeadContent(R.drawable.shop_return_icon,"应用商店",R.drawable.shop_search_icon,null);
    }

    @Override
    public void initData() {
        Map<String,Object> map=new HashMap<>();
        map.put("arg0","userID");
        mainPresenter.reqShopAppData(map);
    }

    @Override
    public void initListener() {
        shopHead.setOnHeadItemClickListener(new HeadView.onHeadItemClickListener() {
            @Override
            public void onHeadReturn(View view) {

            }

            @Override
            public void onHeadRight(View view) {

            }
        });
    }

    @Override
    public void showMsg(String object) {
        Log.d("执行",object);
        Toast.makeText(mContext, "获取成功", Toast.LENGTH_SHORT).show();
        if (object!=null){
            if (showData!=null)
                showData.clear();
            showData= JSON.parseArray(object,MainResultData.class);
            initAdapter();
        }

    }

    @Override
    public void errorMsg(String msg) {
        Log.d("执行",msg);
        Toast.makeText(mContext, "获取失败", Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("WrongConstant")
    private void initAdapter(){
        mShopAdapter=new ShopAdapter(mContext,showData);
        LinearLayoutManager viewManager=new LinearLayoutManager(mContext);
        viewManager.setOrientation(OrientationHelper.VERTICAL);
        shopRecyView.setLayoutManager(viewManager);
        shopRecyView.setAdapter(mShopAdapter);
        mShopAdapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MainResultData item, int position) {
                Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.dc.shopapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dc.shopapp.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
    @BindView(R.id.appSearchBarBg)
    View appSearchBarBg;
    @BindView(R.id.appSearchTvCancel)
    TextView appSearchTvCancel;
    @BindView(R.id.appSearchIvIcon)
    ImageView appSearchIvIcon;
    @BindView(R.id.appSearchEtKeyword)
    EditText appSearchEtKeyword;
    @BindView(R.id.ppSearchResultIvEmpty)
    ImageView ppSearchResultIvEmpty;
    @BindView(R.id.ppSearchResultTvEmpty)
    TextView ppSearchResultTvEmpty;
    @BindView(R.id.ppSearchResultEmptyGroup)
    Group ppSearchResultEmptyGroup;
    @BindView(R.id.appSearchHistoryIvEmpty)
    ImageView appSearchHistoryIvEmpty;
    @BindView(R.id.appSearchHistoryTvEmpty)
    TextView appSearchHistoryTvEmpty;
    @BindView(R.id.appSearchHistoryEmptyGroup)
    Group appSearchHistoryEmptyGroup;
    @BindView(R.id.appSearchTvHistory)
    TextView appSearchTvHistory;
    @BindView(R.id.appSearchTvClean)
    TextView appSearchTvClean;
    @BindView(R.id.appSearchIvClean)
    ImageView appSearchIvClean;
    @BindView(R.id.appSearchHistoryRv)
    RecyclerView appSearchHistoryRv;
    @BindView(R.id.appSearchHistoryGroup)
    Group appSearchHistoryGroup;
    @BindView(R.id.appSearchResultRv)
    RecyclerView appSearchResultRv;



    @Override
    public int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
//        获取本地或网络查询记录

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}

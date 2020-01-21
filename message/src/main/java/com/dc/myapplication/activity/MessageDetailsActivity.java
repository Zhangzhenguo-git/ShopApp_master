package com.dc.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dc.myapplication.R;
import com.dc.myapplication.base.BaseActivity;
import com.dc.myapplication.bean.MessageResultData;
import com.dc.myapplication.weight.HeadView;

import butterknife.BindView;


public class MessageDetailsActivity extends BaseActivity {
    @BindView(R.id.shopMessageDetailsActionBar)
    HeadView shopMessageDetailsActionBar;
    @BindView(R.id.shopMsgNoticeTitle)
    TextView shopMsgNoticeTitle;
    @BindView(R.id.shopMsgNoticeMsgType)
    TextView shopMsgNoticeMsgType;
    @BindView(R.id.shopMsgNoticeframeLayout)
    FrameLayout shopMsgNoticeframeLayout;
    @BindView(R.id.shopMsgNoticeAppType)
    TextView shopMsgNoticeAppType;
    @BindView(R.id.shopMsgNoticeTime)
    TextView shopMsgNoticeTime;
    @BindView(R.id.shopMessageDetailsInfo)
    LinearLayout shopMessageDetailsInfo;
    @BindView(R.id.shopMsgNoticeWebView)
    WebView shopMsgNoticeWebView;
    private Context mContext;
    MessageResultData messageItem;

    @Override
    public int setContentView() {
        return R.layout.activity_message_details;
    }

    @Override
    public void initView() {
        mContext = MessageDetailsActivity.this;
        shopMessageDetailsActionBar.setHeadShowOrHide(View.VISIBLE, View.VISIBLE, View.GONE, View.GONE);
        shopMessageDetailsActionBar.setHeadContent(R.drawable.msg_return_icon, "消息详情", R.drawable.msg_search_icon, null);
        messageItem = (MessageResultData) getIntent().getSerializableExtra("message");
        if (messageItem != null) {
            shopMsgNoticeTitle.setText(messageItem.getTitle());
            shopMsgNoticeTime.setText(messageItem.getIntime());
            shopMsgNoticeAppType.setText(messageItem.getType().toString());
            if (messageItem.getMsgType() != null && "m".equals(messageItem.getMsgType())) {
                shopMsgNoticeMsgType.setText("图文");
            } else {
                shopMsgNoticeMsgType.setVisibility(View.GONE);
            }
            String header = "<header><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no'><style> img{width: 100%;} iframe{width: 100%;} body{padding: 6px;}</style></header>";
            shopMsgNoticeWebView.loadData(header + messageItem.getJsonContent(), "text/html; charset=UTF-8", null);

        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        shopMessageDetailsActionBar.setOnHeadItemClickListener(new HeadView.onHeadItemClickListener() {
            @Override
            public void onHeadReturn(View view) {
                setResult(0, new Intent());
                finish();
            }

            @Override
            public void onHeadRight(View view) {

            }

            @Override
            public void onHeadTextRight(View v) {

            }
        });
    }

    public static Intent getIntent(Context context, String msgType, MessageResultData message) {
        Intent intent = new Intent(context, MessageDetailsActivity.class);
        intent.putExtra("messageType", msgType);
        intent.putExtra("message", message);
        return intent;
    }

}

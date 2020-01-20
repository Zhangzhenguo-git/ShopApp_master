package com.dc.shopapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.dc.shopapp.adapter.MessageAdapter;
import com.dc.shopapp.base.BaseActivity;
import com.dc.shopapp.bean.MessageResultData;
import com.dc.shopapp.presenter.MessagePresenter;
import com.dc.shopapp.view.MessageCallback;
import com.dc.shopapp.weight.CustomMessageDetailsDialog;
import com.dc.shopapp.weight.HeadView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Zhangzhenguo
 * @create 2020/1/19
 * @Email 18311371235@163.com
 * @Describe
 */
public class MessageActivity extends BaseActivity implements MessageCallback {
    @BindView(R.id.msgHeadView)
    HeadView msgHeadView;
    @BindView(R.id.msgRecyView)
    RecyclerView msgRecyView;
    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.shop_Msg_RadioButtonAll)
    RadioButton shopMsgRadioButtonAll;
    @BindView(R.id.shop_Msg_RadioGroupAlllayout)
    RadioGroup shopMsgRadioGroupAlllayout;
    @BindView(R.id.shop_Msg_Delete)
    TextView shopMsgDelete;
    @BindView(R.id.fragment_message_bottomlayout)
    RelativeLayout fragmentMessageBottomlayout;
    private Context mContext;
    private MessagePresenter messagePresenter;
    private Handler mHandler;
    private List<MessageResultData> showData;
    private MessageAdapter messageAdapter;
    private CustomMessageDetailsDialog detailsDialog;

    @Override
    public int setContentView() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        this.mContext = MessageActivity.this;
        msgHeadView.setHeadShowOrHide(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.GONE);
        msgHeadView.setHeadContent(R.drawable.shop_return_icon, "应用商店", R.drawable.shop_search_icon, null);
        mHandler = new Handler() {
            @Override
            public void handleMessage( Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        showData = new ArrayList<>();
                        String result = msg.getData().getString("result");
                        showData = JSON.parseArray(result, MessageResultData.class);
                        initAdapter();
                        getMessageData(false);
                        break;

                }
            }
        };
    }

    @Override
    public void initData() {
        messagePresenter = new MessagePresenter(this);
        messagePresenter.getMessage();
    }

    @Override
    public void initListener() {
        msgHeadView.setOnHeadItemClickListener(new HeadView.onHeadItemClickListener() {
            @Override
            public void onHeadReturn(View view) {

            }

            @Override
            public void onHeadRight(View view) {
                if (msgHeadView.getHeadRightView().equals("编辑")) {
                    reSetMessageIsEdit(true, false,"取消");
                } else if (msgHeadView.getHeadRightView().equals("取消")) {
                    reSetMessageIsEdit(false, false,"编辑");
                }
            }
        });

        //添加/移除-全选
        shopMsgRadioButtonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!shopMsgRadioButtonAll.isSelected()) {
                    messageAdapter.selectedCount = showData.size() - 1;
                    addAllSelected(true);
                    reSetMessageIsAllSelected(true);
//                    Log.d("fdsf ",science_Msg_RadioButtonAll.isSelected()+"添加全选");
                } else {
                    messageAdapter.selectedCount = -1;
                    addAllSelected(false);
                    reSetMessageIsAllSelected(false);
//                    Log.d("fdsf ",science_Msg_RadioButtonAll.isSelected()+"移除全选");
                }
            }
        });
        shopMsgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageAdapter==null){
                    return;
                }
                if (messageAdapter.selectedCount > -1) {
                    if (detailsDialog!=null){
                        detailsDialog=null;
                        detailsDialog=new CustomMessageDetailsDialog(mContext);
                    }else {
                        detailsDialog=new CustomMessageDetailsDialog(mContext);
                    }
                    detailsDialog.getTvTitle().setText("删除提醒");
                    detailsDialog.getTvContent().setText("确认要删除消息吗？");
                    detailsDialog.getTvContent().setGravity(Gravity.CENTER);
                    detailsDialog.getTvCancel(false).setText("取消");
                    detailsDialog.getTvCancel(false).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            detailsDialog.dismiss();
                        }
                    });
                    detailsDialog.getTvConfirm().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final ArrayList<String> pids = new ArrayList<>();
                            //im删除
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < showData.size(); i++) {
                                        if (showData.get(i).isSelected()) {
                                            pids.add(showData.get(i).getDescription());
                                            System.out.println("messageData.get(i).getMessageItem().getDescription()"+showData.get(i).getDescription());
                                        }
                                    }
                                    Message message=new Message();
                                    message.what=1;
                                    Bundle bundle=new Bundle();
                                    bundle.putStringArrayList("keys",pids);
                                    message.setData(bundle);
                                    mHandler.sendMessage(message);
                                }
                            }).start();
                        }
                    });
                    detailsDialog.show();
                }else {
                    Toast.makeText(mContext, "请选择要删除的消息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        Log.d("执行",result);
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        msg.what = 1;
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    @Override
    public void onError(String msg) {

    }
    /**
     * 获取消息数据
     * @param isEdit 是否编辑
     */
    private void getMessageData(boolean isEdit) {
            //接收消息数据
            if (showData != null && showData.size() > 0) {
                //n次数据进行添加刷新
                setMessageData(isEdit,showData.size() - 1,showData.get(showData.size()-1));
            } else {
                //初始化数据
                setMessageData(isEdit,-1,showData.get(showData.size()-1));
            }
    }

    /**
     * 设置数据
     * @param isEdit 是否编辑
     * @param dataId 当前显示的ItemID
     */
    private void setMessageData(boolean isEdit,int dataId,MessageResultData info) {
        //清空选中状态标记数
        messageAdapter.selectedCount=-1;
        //刷新成功后，修改消息个数
        showMessageStatusCount(info);
        //刷新成功后，取消全选状态
        addAllSelected(false);
        if (messageAdapter!=null){
            messageAdapter.notifyDataSetChanged();
        }else {
            initAdapter();
        }
    }
    @SuppressLint("WrongConstant")
    private void initAdapter() {
        messageAdapter = new MessageAdapter(mContext, showData, null);
        LinearLayoutManager viewManager = new LinearLayoutManager(mContext);
        viewManager.setOrientation(OrientationHelper.VERTICAL);
        msgRecyView.setLayoutManager(viewManager);
        msgRecyView.setAdapter(messageAdapter);
        messageAdapter.setMessageOnItemClickListener(new MessageAdapter.MessageOnItemClickListener() {
            @Override
            public void onItemClick(int mPosition, MessageResultData appItem, boolean isPlugin, boolean isRead) {
                final MessageResultData mItem = showData.get(mPosition);
                if (!msgHeadView.getHeadRightView().equals("取消")) {
                    if (mItem.getMsgType() != null) {
                        startActivityForResult(MessageDetailsActivity.getIntent(mContext, mItem.getMsgType().toString(), mItem), 1);
                        messageAdapter.notifyDataSetChanged();

                    } else {
//                        根据需求添加相应展示，如弹框展示
                    }
                }
            }

            @Override
            public void onItemRadioButton(boolean isAllSelected, List<MessageResultData> mMessageData) {

            }
        });
    }

    /**
     * 重新设置适配器数据是否为编辑状态
     *
     * @param isEdit     设置是否编辑状态
     * @param isSelected 设置是否选中状态
     */
    private void reSetMessageIsEdit(boolean isEdit, boolean isSelected, String isEditOrCancel) {
        if (showData != null && showData.size() > 0) {
            msgHeadView.setHeadRightEdit(isEditOrCancel);
            msgHeadView.getHeadRightView().setVisibility(View.VISIBLE);
        } else {
            msgHeadView.getHeadRightView().setVisibility(View.GONE);
        }

        //重新设置选中标记数
        messageAdapter.selectedCount = -1;
        for (MessageResultData data : showData) {
            data.setEdit(isEdit);
            data.setSelected(isSelected);
        }
        if (isEdit) {
            fragmentMessageBottomlayout.setVisibility(View.VISIBLE);
        } else {
            fragmentMessageBottomlayout.setVisibility(View.GONE);
        }
        addAllSelected(false);
        if (messageAdapter == null) {
            return;
        }
        messageAdapter.notifyDataSetChanged();
    }

    /**
     * 重新设置适配器数据是否为全选和取消全选状态
     *
     * @param isSelected 设置是否选中状态
     */
    private void reSetMessageIsAllSelected(boolean isSelected) {
        for (MessageResultData data : showData) {
            data.setSelected(isSelected);
        }
        if (messageAdapter == null) {
            return;
        }
        messageAdapter.notifyDataSetChanged();
    }

    /**
     * 添加/移除全选
     *
     * @param isTrue
     */
    private void addAllSelected(boolean isTrue) {
        shopMsgRadioGroupAlllayout.clearCheck();
        shopMsgRadioButtonAll.setChecked(isTrue);
        shopMsgRadioButtonAll.setSelected(isTrue);
    }

    /**
     * 清空数据
     */
    public void clearAndRestData() {
        if (showData != null && showData.size() > 0) {
            showData.clear();
        }
        if (msgHeadView.getHeadRightView().equals("取消")) {
            getMessageData(true);
        } else {
            getMessageData(false);
        }
    }

    /**
     * 显示消息的未读和总数
     */
    public void showMessageStatusCount(MessageResultData info) {
        if (info.getTotalCnt() == 0) {
            msgHeadView.getHeadRightView().setVisibility(View.GONE);
        } else if (info.getTotalCnt() > 0) {
            msgHeadView.getHeadRightView().setVisibility(View.VISIBLE);
        }
        msgHeadView.setHeadCenterTitle("消息（" + info.getNotReadCnt() + "/" + info.getTotalCnt() + "）");
        sendEventBus(String.valueOf(info.getNotReadCnt()));
    }

    /**
     * EventBus发送样例
     */
    private void sendEventBus(String msgStatusCount) {
//        可实现如微信未读消息标记
    }

    /**
     * 重置编辑状态
     */
    public void setEditStatus() {
        if (msgHeadView.getHeadRightView().equals("取消")) {
            reSetMessageIsEdit(false, false, "编辑");
        }
    }

}
